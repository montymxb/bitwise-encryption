
##clean up
rm -rf build
rm -rf jar

##reconstruct
mkdir build
mkdir jar

cd src

##compile code into build folder
javac -d ../build *.java

cd ../build

##create executable jar
jar cvfe ../jar/BitWise.jar BitWise *.class

cd ../jar

##run the code
java -jar BitWise.jar