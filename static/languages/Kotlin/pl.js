let header = document.getElementsByTagName("header")[0];
let title = header.innerText;

header.innerHTML = `<h1>${title}</h1>\n<hr/>`;

let subheaders = document.getElementsByClassName("subheader");
for (let i = 0; i < subheaders.length; i++) subheaders[i].innerHTML = `<hr/><h2>${subheaders[i].innerText}</h2><hr/>`;

let codeBlocks = document.getElementsByClassName("code-block");
for (let i = 0; i < codeBlocks.length; i++) codeBlocks[i].innerHTML = codeBlocks[i].innerHTML.replace(/^[^|]*\|+/gm, "").replace(/\\t/gm, "&nbsp;&nbsp;&nbsp;");