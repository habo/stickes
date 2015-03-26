# Stickes
Java based embroidery software

Special features: 
* fractals
* turtle programming pattern
* QR-Code generation

Required: maven3, JSDK1.7

License is GPLv3 without commercial use for declared files.
Parts are under zlib-license, see other-licenses/ZLIB-LICENSE.txt for details

## run visual editor
mvn compile exec:java 

## run Sonar integration
mvn compile sonar:sonar

## create example files in Directory "target/output"
mvn compile exec:java -Dexec.mainClass="de.xonibo.stickes.examples.CreateExamples"

## clean up everything
mvn clean 

## Contact 
habo@dingfabrik.de

