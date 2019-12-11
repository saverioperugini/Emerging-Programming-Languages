let recursive = require("recursive-readdir");
let express = require("express");
let fs = require('fs');
let app = express();

function getIndex(key, array) {
	for (let i = 0; i < array.length; i++) {
		if (array[i].language === key) {
			return i;
		}
	}
	return -1;
}

function getIndexFinal(key, array) {
	for (let i = 0; i < array.length; i++) {
		if (array[i].final === key) {
			return i;
		}
	}
	return -1;
}

app.get("/", function(req, res) {
	let languages = [];
	let languagesObj = [];
	
	recursive("static/languages", function(err, files) {
		files = files.map(path => path.replace(/\\/gm, "/")).sort();
		
		files.forEach(item => {
			if (item.includes("html") || item.includes("pdf")) {
				let parts = item.split("/");
				if (languages.includes(parts[2])) {
					let i = getIndex(parts[2], languagesObj);
					
					if(item.includes("html")) {
						languagesObj[i].notes.path = parts.slice(1).join("/")
					} else if(parts.includes('reference')) {
						languagesObj[i].reference.path = parts.slice(1).join("/")
					} else if(parts.includes('synopsis')) {
						languagesObj[i].synopsis.path = parts.slice(1).join("/")
					} else if(parts.includes('presentation')) {
						languagesObj[i].slides.path = parts.slice(1).join("/")
					}
					
				} else {
					languages.push(parts[2]);
					
					let lang = {
						language: parts[2],
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
						lang.notes.path = parts.slice(1).join("/")
					} else if(parts.includes('reference')) {
						lang.reference.path = parts.slice(1).join("/")
					} else if(parts.includes('synopsis')) {
						lang.synopsis.path = parts.slice(1).join("/")
					}
					
					languagesObj.push(lang);
				}
			}
		});
		
		res.send(languagesObj);
	});
});

app.get("/finals", function(req, res) {
	let finals = [];
	let finalsObj = [];
	
	recursive("static/finals", function(err, files) {
		files = files.map(path => path.replace(/\\/gm, "/")).sort();
		
		files.forEach(item => {
			if (item.includes("pdf") || item.includes("html")) {
				let parts = item.split("/");
				let names = parts[2].split("-");
				if (finals.includes(names[0])) {
					let i = getIndexFinal(names[0], finalsObj);
					
					if(parts.includes("paper")) {
						finalsObj[i].paper.path = parts.slice(1).join("/")
					} else if(parts.includes('presentation')) {
						finalsObj[i].slides.path = parts.slice(1).join("/")
					} else if(item.includes('html') && !item.includes('src')) {
						finalsObj[i].abstract = fs.readFileSync(item, "utf8");
					}
					
				} else {
					finals.push(names[0]);
					
					let final = {
						final: names[0],
						abstract: '',
						paper: {
							modal: false,
							path: ''
						},
						slides: {
							modal: false,
							path: ''
						}
					};
					
					if(parts.includes("paper")) {
						final.paper.path = parts.slice(1).join("/")
					} else if(parts.includes('presentation')) {
						final.slides.path = parts.slice(1).join("/")
					} else if(item.includes('html') && !item.includes('src')) {
						final.abstract = fs.readFileSync(item, "utf8");
					}
					
					finalsObj.push(final);
				}
			}
		});
		
		res.send(finalsObj);
	});
});

module.exports = {
	path: "/languages/",
	handler: app
};
