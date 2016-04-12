PROJDIR=..
JARNAME=po-uilib

all:
	(cd src; javac -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cvf $(PROJDIR)/$(JARNAME).jar `find . -name \*.class -o -name \*.java`)

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`

