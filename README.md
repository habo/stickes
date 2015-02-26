# Stickes
Java based embroidery software

License is GPLv3 without commercial use for declared files.
Parts are under zlib-license, see other-licenses/ZLIB-LICENSE.txt for details

## run visual editor
mvn exec:java 

## run Sonar integration
mvn install sonar:sonar

## create example files in target/output
mvn exec:java -Dexec.mainClass="de.xonibo.stickes.examples.CreateExamples"

## clean 
mvn clean 

## Contact 
habo@dingfabrik.de

