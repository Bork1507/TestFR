mkdir bin
echo "Compiling TestFR..."
javac -d ./bin -sourcepath ./java/src -classpath ./java/lib/sqlite-jdbc-3.7.2.jar:./java/lib/crunchify.jar:./java/lib/jssc.jar ./java/src/TestFR.java
echo "Making headers for JNI..."
javah -d ./cpp/src/windows -classpath ./bin SHTRIHDRV_JNI
echo "Compiling libraries for JNI..."
# not yet implemented ... g++ -shared -Wl,--kill -I./cpp ./cpp/src/linux/SHTRIHDRV_JNI.cpp -o ./java/lib/linux/SHTRIHDRV_JNI.so -lole32 -luuid -loleaut32
read -p "Press any key..."
