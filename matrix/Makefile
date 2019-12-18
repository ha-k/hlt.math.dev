# FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/Makefile
# EDIT BY . . . Hassan Ait-Kaci
# ON MACHINE. . Hak-Laptop
# STARTED ON. . Fri Nov 15 11:34:00 2019

# Last modified on Wed Dec 18 10:04:34 2019 by hak

########################################################################

# NB: make does not understand /cygdrive/d - its needs D:
WIN_HOME	= D:/hak

HLT_HOME	= ${WIN_HOME}/hlt

HLT_CLASSES	= ${HLT_HOME}/classes
CLASSPATH	= .;${HLT_CLASSES}

#JDOM_CLASSES	= ${HLT_CLASSES}/jdom.jar# useful only if processing XML files
#CLASSPATH	= .;${HLT_CLASSES};${JDOM_CLASSES}#JDOM useful only if processing XML files

RESOURCES	= ${HLT_CLASSES}/resources

SOURCEROOT	= $(HLT_HOME)/src
SOURCESDIR	= sources/
SOURCES		= ${SOURCESDIR}*.java

SAFESOURCES	= ${SOURCESDIR}BipartiteGraph.java\
		  ${SOURCESDIR}IncompatibleMatrixDimensionException.java\
		  ${SOURCESDIR}Matrix.java\
		  ${SOURCESDIR}MaxMinAlgebra.java\
		  ${SOURCESDIR}NumberAlgebra.java\
		  ${SOURCESDIR}StandardAlgebra.java

TEST		= test
TESTMAIN	= Test
#TESTMAIN	= DecimalFormatTest

DOCDIR		= ${HLT_HOME}/doc/hlt/code/math/matrix/
LOCALDOCDIR	= ./doc/

#APIDIR		= $(HLT_HOME)/doc/hlt/api
#temp while developing:
APIDIR		= ./api/

DOCDIRLISTING	= 000StartHere.html

DOCOPTIONS	= -html4 -windowtitle "API documentation of hlt.math.matrix package" \
                  -author -version -public $(TAGS) \
                  -header "Author: <a href=\"http://www.hassan-ait-kaci.net\">Hassan A&iuml;t-Kaci</a>" \
                  -footer "Contact: <a href=\"mailto:hak@acm.org?subject=hlt.math.matrix\">hak@acm.org</a>"

SYSTEM		= hlt.math
PACKAGES	= $(SYSTEM).matrix

QUIET		= -quiet ${HUSH}
HUSH		= > /dev/null 2> /dev/null

# TRASH	= ${LOCALDOCDIR},* ${SOURCESDIR},*.java ,Makefile ${TEST}/,*.java */,.* */*~ */@*@ */.*~
# Why does't this work:
# TRASH	= ,* ,.* *~ .*~ @*@

# trash files here and all the subdirs
TRASH	= ,* ,.* *~ .*~ @*@\
	  */,* */,.* */*~ */.*~ */@*@\
	  */*/,* */*/,.* */*/*~ */*/.*~ */*/@*@

JCT	= @javac -classpath "$(CLASSPATH)" -O -Xlint:deprecation #-Xlint:unchecked
JC	= ${JCT} -d "$(HLT_CLASSES)" 
JV	= @java -classpath "$(CLASSPATH)"

MK	= @/usr/bin/make
ND	= @/usr/bin/mkdir -p
CP	= @/usr/bin/cp -f
CD	= @/usr/bin/cd
LS	= @/usr/bin/ls
MV	= @/usr/bin/mv -f
RM	= @/usr/bin/rm -f -r
CL	= @/usr/bin/rm -f -r ${TRASH} 2> /dev/null || true

SAY	= @echo "***"

HL_OPS	= -! -c "${RESOURCES}/Hilite.Configuration" -css style.css
HL	= @java -classpath "$(HLT_CLASSES)" hlt.language.tools.HiliteCommand \
	  -css style.css \
	  -con $(HLT_CLASSES)/resources/Hilite.Configuration.Jacc -s /

########################################################################

help:
	${SAY}
	${SAY} "This makefile manages the hlt.math.matrix package."
	${SAY} "You can 'make ...' where '...' is one of:"
	${SAY}
	${SAY} "    all:      same as 'classes doc save'"
#	${SAY} "    all:      same as 'classes doc api'"
	${SAY} "    classes:  compiles the hlt.math.matrix/*.java source files"
	${SAY} "    test:     compiles and runs the ./test/Test.java source file"
	${SAY} "    doc:      generates the hlt.math/matrix*.html source files documentation"
	${SAY} "    api:      generates  hlt.math.matrix/* API javadoc documentation"
	${SAY} "    save:     save the sources of this directory (~/hlt/src/math/matrix/)"
	${SAY} "    tidy:     deletes trash"
	${SAY} "    clean:    erases regeneratable files (class, html)"
	${SAY} "    help:     prints this message"
	${SAY}

########################################################################

all:	classes doc save
#all:	classes doc api

########################################################################

classes: tidy
	${SAY} Removing existing hlt.math.matrix class files
	${RM} ${HLT_CLASSES}/hlt/math/matrix/*.class
	${SAY} Compiling hlt.math.matrix package sources
	${JC} ${SOURCES}

########################################################################

test: tidy
	${SAY} Removing ${TEST}/*.class "file(s)"
	${RM} ${TEST}/*.class
	${SAY} Compiling ${TEST}/*.java "source(s)"
	${JCT} ${TEST}/*.java
	${SAY} Running ${TEST}/${TESTMAIN}
	${JV} ${TEST}/${TESTMAIN}

########################################################################

api: tidy
#	$(SAY) Regenerating the Javadoc API documentation for all the hlt.math.matrix package
#	@make -C $(HLT_HOME)/src all
	$(SAY) Generating Javadoc API documentation for hlt.math.matrix package
	@javadoc -J-Xmx128m -classpath "$(CLASSPATH)" -sourcepath "$(SOURCEROOT)"\
		 -d "$(APIDIR)" $(DOCOPTIONS) $(PACKAGES) $(QUIET)
	$(SAY) Wrote javadoc root file $(APIDIR)/index.html

########################################################################

doc: tidy
	${SAY} Removing old ${DOCDIR} documentation
	${RM} ${LOCALDOCDIR}*.html ${DOCDIR}*.html
	${SAY} Generating HTML docs for math.matrix hlt.math.matrix source files
	${HL} -d ${LOCALDOCDIR} ${SOURCES}
	${MV} ${LOCALDOCDIR}index.html ${LOCALDOCDIR}${DOCDIRLISTING}
	${CP} ${LOCALDOCDIR}*.* ${DOCDIR}
	${SAY} See hilited HTML source code in ${DOCDIR}${DOCDIRLISTING}

########################################################################

save: export
	${SAY} "Saving java sources in directory ~/hlt/src/math/matrix/sources"
	${RM} test/*.class test/*.html $(HUSH)
	@tar cvf hlt.math.matrix.tar Makefile ${SOURCES} test $(HUSH)
	@gzip hlt.math.matrix.tar
	${MV} hlt.math.matrix.tar.gz ./save/
	${SAY} Wrote ./save/hlt.math.matrix.tar.gz

########################################################################

export: tidy
	${SAY} "Exporting safe java sources in directory ~/hlt/src/math/matrix/sources"
	${CP} ${SAFESOURCES} ../../matrix/
	${SAY} "Wrote safe java sources in directory ../../matrix"

########################################################################

clean: tidy
	${SAY} "Deleting regeneratable class and html files"
	${RM} ${HLT_CLASSES}/hlt/math/matrix/*.class
	${RM} ${TEST}/*.class
	${RM} ${HLT_HOME}/doc/hlt/code/math/matrix/*.html
	${RM} ${HLT_HOME}/api/hlt/math/matrix/*.html

########################################################################

tidy:
	${SAY} "Tidying up trash"
	${LS} ${TRASH} 2> /dev/null || true
	${RM} ${TRASH} 2> /dev/null || true


########################################################################
