all:
	javac Graph.java

.PHONY: clean

clean:
	rm *.class > /dev/null
