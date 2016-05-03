HTMLDIR = html
MDDIR = spec
SOURCES = spec_lemming.md spec_level.md spec_gameeng.md spec_joueur.md
TARGETS = $(SOURCES:.md=.html)

all: doc

%.html: %.md
	pandoc $< -s -o $@

doc: $(TARGETS)
	mv $(TARGETS) html/

clean:
	rm -f $(TARGETS)
