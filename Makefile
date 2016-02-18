
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	DecisionTreeClassifier.java \
	Word.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
