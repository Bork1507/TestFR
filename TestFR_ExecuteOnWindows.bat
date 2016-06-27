md bin
echo "Compile TestFR..."
rem C:\Progra~1\Java\jdk1.8.0_91\bin\
javac -d bin -sourcepath src -classpath lib\sqlite-jdbc-3.7.2.jar;lib\crunchify.jar;lib\jssc.jar -encoding "UTF-8" src\TestFR.java
echo "Run TestFR..."
java -classpath bin;lib\sqlite-jdbc-3.7.2.jar;lib\crunchify.jar;lib\jssc.jar TestFR
pause