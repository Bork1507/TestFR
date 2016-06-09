md bin
echo "Compile TestFR..."
javac -d bin -sourcepath src -classpath lib\sqlite-jdbc-3.7.2.jar:lib\crunchify.jar:lib\jssc.jar src\TestFR.java
echo "Run TestFR..."
java -classpath bin;lib\sqlite-jdbc-3.7.2.jar;lib\crunchify.jar;lib\jssc.jar TestFR
pause