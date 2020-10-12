run: playfair.class
	java playfair $(ARGS)

playfair.class: playfair.java
	javac playfair.java

clean:
	rm *.class
