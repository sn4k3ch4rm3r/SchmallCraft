default: 
	if not exist "build" mkdir "build
	pandoc specification.md --template "docs_template.tex" -o "build/dokumentacio.pdf" --variable neptun=$(NEPTUN)

clean:
	rmdir /S /Q "build"