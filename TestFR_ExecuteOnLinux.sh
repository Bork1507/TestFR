echo "Run TestFR..."
java -classpath .:./bin:./java/lib/sqlite-jdbc-3.7.2.jar:./java/lib/crunchify.jar:./java/lib/jssc.jar TestFR
read -p "Press any key..."
