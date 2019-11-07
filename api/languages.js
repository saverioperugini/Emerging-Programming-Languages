var recursive = require("recursive-readdir");
var fs = require("fs");
var express = require("express");
var app = express();

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
    files.forEach(item => {
      var parts = item.split("/");
      if (languages.includes(parts[1])) {
        let i = getIndex(parts[1], languagesObj);
        languagesObj[i].files.push(item);
      } else {
        languages.push(parts[1]);
        var lang = {
          language: parts[1],
          files: []
        };
        lang.files.push(item);
        languagesObj.push(lang);
      }
    });

    res.send(languagesObj);
  });
});

module.exports = {
  path: "/languages/",
  handler: app
};
