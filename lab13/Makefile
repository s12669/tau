

all: build/vectorlib.o

build/vectorlib.o: src/vectorlib.cpp
	g++ -std=c++11 -c src/vectorlib.cpp -o build/vectorlib.o

tests: unit
	./unit -r junit
	./unit -s

unit: build/unit_vectorlib.o build/units.o build/vectorlib.o
	g++ build/unit_vectorlib.o build/units.o build/vectorlib.o -o unit


build/units.o: src/tests/units.cpp
	g++ -std=c++11 -Isrc -Ithirdparty -c src/tests/units.cpp -o build/units.o

build/unit_vectorlib.o: src/tests/unit_vectorlib.cpp
	g++ -std=c++11 -Isrc -Ithirdparty  -c src/tests/unit_vectorlib.cpp -o build/unit_vectorlib.o

clean:
	rm -f build/*.o unit

