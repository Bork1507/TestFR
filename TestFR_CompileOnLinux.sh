mkdir bin
echo "Compiling TestFR..."
javac -d ./bin -sourcepath ./java/src -classpath ./java/lib/sqlite-jdbc-3.7.2.jar:./java/lib/crunchify.jar:./java/lib/jssc.jar ./java/src/TestFR.java
echo "Making headers for JNI..."
javah -d ./cpp/src -classpath ./bin SHTRIHDRV_JNI
echo "Compiling libraries for JNI..."
g++ -fPIC -shared -I./cpp/src -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux ./cpp/src/linux/SHTRIHDRV_JNI.cpp -o ./java/lib/linux/SHTRIHDRV_JNI.so
#read -p "Press any key..."
