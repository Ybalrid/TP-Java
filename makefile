all:
	javac *.java
.PHONY: clean

clean:
	rm *.class 2> /dev/null
