#PO_UILIB_DIR=../..
#PO_UILIB_DIR=../../po-uilib
PO_UILIB_DIR=../../..
# Updated to point to the correct location of po-uilib.jar (one directory above 122)
JARNAME=bci-app

all:
	(cd src; javac -cp $(PO_UILIB_DIR)/po-uilib.jar:../../bci-core/bci-core.jar -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`