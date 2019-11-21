let recursive = require("recursive-readdir");
let fs = require("fs");
let express = require("express");
let ncp = require("ncp").ncp;
let app = express();
ncp.limit = 16;

function getIndex(key, array) {
  for (i = 0; i < array.length; i++) {
    if (array[i].language == key) {
      return i;
    }
  }
  return -1;
}

app.get("/", function(req, res) {
  let languages = [];
  let languagesObj = [];
  recursive("student-languages", function(err, files) {
    ncp("student-languages", "static", function(err) {
      if (err) {
        console.error(err);
      }
      console.log("done!");
    });
    
    files = files.map(path => path.replace(/\\/gm, "/"));
    
    files.forEach(item => {
      if (item.includes("html") || item.includes("pdf")) {
        let parts = item.split("/");
        if (languages.includes(parts[1])) {
          let i = getIndex(parts[1], languagesObj);

          let f = {
            name: parts[parts.length - 1],
            path: "http://localhost:3000/" + parts.slice(1).join("/")
          };

          languagesObj[i].files.push(f);
        } else {
          languages.push(parts[1]);

          let lang = {
            language: parts[1],
            files: [],
            on: false
          };

          /*let d = JSON.parse(JSON.stringify(parts));
          d.shift();
          d.pop();
          d = "static/" + d.join("/") + "/pl.css";

          fs.copyFile("static/pl.css", d, err => {
            if (err) throw err;
            console.log("source.txt was copied to destination.txt");
          });*/

          let f = {
            name: parts[parts.length - 1],
            path: "http://localhost:3000/" + parts.slice(1).join("/")
          };

          lang.files.push(f);
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
