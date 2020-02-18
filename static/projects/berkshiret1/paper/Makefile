SRC = berkshiret1-paper
all:
	pdflatex $(SRC)
	bibtex $(SRC)
	pdflatex $(SRC)
	pdflatex $(SRC)

clean:
	rm *.pdf $(SRC).log $(SRC).out $(SRC).aux $(SRC).bbl $(SRC).blg comment.cut