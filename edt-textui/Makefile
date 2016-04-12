PROJDIR=..
JARNAME=edt-textui

all:
	(cd src; javac -cp $(PROJDIR)/po-uilib.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cvf $(PROJDIR)/$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`


