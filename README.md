# CPS 452: Emerging Programming Languages Website

> Emerging Programming Languages

## Add New Languages

1.) Add new language directory to /static/languages (ensuring the directory name is the title of the language)

2.) In /pages/index.vue add the key-value pairs to presentations and logos around lines 95 & 105 respectively (if one or both exists)
* Key needs to match the corresponding language directory title exactly
* Logos can be found anywhere on the internet
* YouTube links can be found by selecting "Share" on the YouTube page, choosing embed, and copying the contents of the src attribute in the provided iframe tag

## Language Directory Structure

* HTML should be titled `index.html` and be in the root directory

* PowerPoints should be in PDF form in a `presentation` directory

* Formal papers should be in PDF form in a `synopsis` directory

* Quick reference sheets should be in PDF form in a `reference` directory

* All code should be located in a `src` directory in either `code` or `exercises` as seen fit
```html
$ find arnoldz3-Factor/
arnoldz3-Factor
arnoldz3-Factor/index.html
arnoldz3-Factor/synopsis
arnoldz3-Factor/synopsis/acmart.cls
arnoldz3-Factor/synopsis/index.html
arnoldz3-Factor/synopsis/arnoldz3-Factor.pdf
arnoldz3-Factor/src
arnoldz3-Factor/src/code
arnoldz3-Factor/src/code/my-quadratic.factor
arnoldz3-Factor/src/code/my-factorial.factor
arnoldz3-Factor/src/code/library.factor
arnoldz3-Factor/src/code/palindrome.factor
arnoldz3-Factor/src/code/palindrome-tests.factor
arnoldz3-Factor/src/exercises
arnoldz3-Factor/src/exercises/assignment.factor
```

## Add New Final Projects

1.) Add new language directory to /static/finals

2.) In /pages/projects.vue add a key-value pair to presentations around line 85 (if one exists)
* Key needs to match the creators username exactly as it appears in their folder name
* YouTube link can be found same as above

## Final Project Directory Structure

* HTML should be titled `index.html` and be in the root directory

* PowerPoints should be in PDF form in a `presentation` directory

* Formal papers should be in PDF form in a `paper` directory

* All code should be located in a `src` directory 

```html
$ find arnoldz3-Final/
arnoldz3-Final
arnoldz3-Final/index.html
arnoldz3-Final/paper
arnoldz3-Final/paper/arnoldz3-paper.pdf
arnoldz3-Final/presentation/
arnoldz3-Final/presentation/arnoldz3-presentation.pdf
arnoldz3-Final/src
arnoldz3-Final/src/README
arnoldz3-Final/src/homework.factor
arnoldz3-Final/src/library.factor
arnoldz3-Final/src/my-factorial.factor
arnoldz3-Final/src/my-quadratic.factor
arnoldz3-Final/src/palindrome-tests.factor
arnoldz3-Final/src/palindrome.factor
```

## Abstract HTML Template

```html
<a name="ltalatinian1"/>
<center>
<h3>Image Editor in LOVE2D</h3>

<h3>Luc V. Talatinian</h3>

<b>Abstract</b>
</center>
<blockquote>
An image editing program is developed using the LOVE2D engine. The engine, written in C++,
makes use of Lua scripting to allow users to develop games. Users define callbacks that act
as functions to be executed as part of the main-game loop. An image is loaded into the program,
and is then edited by the user with various tools. All functionality is supplied through the
use of the interactive callbacks supplied by the LOVE2D engine. The editor will encode any
changes made into a .png file upon exiting the program.
</blockquote>

```

## Build Setup

``` bash
# install dependencies
$ npm run install

1.) Serve with hot reload at localhost:3000 to build the necessary JSON files
$ npm run dev

2.) Copy localhost:3000/languages into /static/languages.json

3.) Copy localhost:3000/languages/finals into /static/finals.json

4.) Generate static project for GitHub pages
$ npm run generate

5.) Use subtree push to send it to the gh-pages branch on GitHub.
$ npm run deploy
```

## GitHub Pages Setup

Ensure GitHub pages is set to use the `gh-pages` branch
