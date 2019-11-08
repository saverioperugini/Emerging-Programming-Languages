var recursive = require("recursive-readdir");
var fs = require("fs");
var express = require("express");
var ncp = require("ncp").ncp;
var app = express();
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
  var languages = [];
  var languagesObj = [];
  recursive("student-languages", function(err, files) {
    ncp("student-languages", "static", function(err) {
      if (err) {
        console.error(err);
      }
      console.log("done!");
    });

    files.forEach(item => {
      if (item.includes("html") || item.includes("pdf")) {
        var parts = item.split("/");
        if (languages.includes(parts[1])) {
          let i = getIndex(parts[1], languagesObj);

          var f = {
            name: parts[parts.length - 1],
            path: "http://localhost:3000/" + parts.slice(1).join("/")
          };

          languagesObj[i].files.push(f);
        } else {
          languages.push(parts[1]);

          var lang = {
            language: parts[1],
            files: [],
            on: false
          };

          /*var d = JSON.parse(JSON.stringify(parts));
          d.shift();
          d.pop();
          d = "static/" + d.join("/") + "/pl.css";

          fs.copyFile("static/pl.css", d, err => {
            if (err) throw err;
            console.log("source.txt was copied to destination.txt");
          });*/

          var f = {
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
