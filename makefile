
compile: bin
	javac -cp biuoop-1.4.jar -d bin src/*.java src/Ass7Game.java
run:
	java -cp biuoop-1.4.jar:bin:resources Ass7Game 
jar:
	jar cfm ass7game.jar MANIFEST.MF -C bin . -C resources .
bin:
	mkdir bin
