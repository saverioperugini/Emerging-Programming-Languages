let recursive = require("recursive-readdir");
let express = require("express");
let app = express();

function getIndex(key, array) {
	for (let i = 0; i < array.length; i++) {
		if (array[i].language === key) {
			return i;
		}
	}
	return -1;
}

app.get("/", function(req, res) {
	let languages = [];
	let languagesObj = [];
	
	recursive("static", function(err, files) {
		files = files.map(path => path.replace(/\\/gm, "/")).sort();
		
		files.forEach(item => {
			if (item.includes("html") || item.includes("pdf")) {
				let parts = item.split("/");
				if (languages.includes(parts[1])) {
					let i = getIndex(parts[1], languagesObj);
					
					if(item.includes("html")) {
						languagesObj[i].notes.path = "http://localhost:3000/" + parts.slice(1).join("/")
					} else if(parts.includes('reference')) {
						languagesObj[i].reference.path = "http://localhost:3000/" + parts.slice(1).join("/")
					} else if(parts.includes('synopsis')) {
						languagesObj[i].synopsis.path = "http://localhost:3000/" + parts.slice(1).join("/")
					} else if(parts.includes('presentation')) {
						languagesObj[i].slides.path = "http://localhost:3000/" + parts.slice(1).join("/")
					}
					
				} else {
					languages.push(parts[1]);
					
					let lang = {
						language: parts[1],
						notes: {
							modal: false,
							path: ''
						},
						reference: {
							modal: false,
							path: ''
						},
						synopsis: {
							modal: false,
							path: ''
						},
						slides: {
							modal: false,
							path: ''
						},
						presentation: false
					};
					
					if(item.includes("html")) {
						lang.notes.path = "http://localhost:3000/" + parts.slice(1).join("/")
					} else if(parts.includes('reference')) {
						lang.reference.path = "http://localhost:3000/" + parts.slice(1).join("/")
					} else if(parts.includes('synopsis')) {
						lang.synopsis.path = "http://localhost:3000/" + parts.slice(1).join("/")
					}
					
					languagesObj.push(lang);
				}
			}
		});
		
		res.send(languagesObj);
	});
});

module.exports = {
	path: "/languages/",
	handler: app
};
