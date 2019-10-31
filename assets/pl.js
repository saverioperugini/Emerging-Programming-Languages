$(() => {
	let header = document.getElementsByTagName("header")[0];
	let title = header.innerText;
	
	header.innerHTML = `<h1>${title}</h1>\n<hr/>`;
	
	let subheaders = document.getElementsByClassName("subheader");
	for (let i = 0; i < subheaders.length; i++) {
		title = subheaders[i].innerText;
		subheaders[i].innerHTML = `<h2>${title}</h2>`;
		
		if (i !== 0) $(subheaders[i]).prepend("<hr/>");
		if (i !== subheaders.length - 1) $(subheaders[i]).append(`<hr/>`)
	}
	
	let codeBlocks = document.getElementsByClassName("code-block");
	for (let i = 0; i < codeBlocks.length; i++) {
		let content = codeBlocks[i].innerHTML;
		console.log(content.indexOf("[TAB]"));
		
		while (content.indexOf("[TAB]") >= 0) {
			content = content.replace("[TAB]", "&nbsp;&nbsp;&nbsp;&nbsp;")
		}
		
		codeBlocks[i].innerHTML = content;
	}
});