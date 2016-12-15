mkdir bin
echo "Compiling TestFR..."
javac -d ./bin -sourcepath ./java/src -classpath ./java/lib/sqlite-jdbc-3.7.2.jar:./java/lib/crunchify.jar:./java/lib/jssc.jar ./java/src/TestFR.java
echo "Making headers for JNI..."
javah -d ./cpp/src -classpath ./bin SHTRIHOLE_JNI
javah -d ./cpp/src -classpath ./bin SPDRV_JNI
javah -d ./cpp/src -classpath ./bin SPOLE_JNI
javah -d ./cpp/src -classpath ./bin SPOLE1C_JNI
echo "Compiling libraries for JNI..."
g++ -fPIC -shared -I./cpp/src -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux ./cpp/src/linux/SHTRIHOLE_JNI.cpp -o ./java/lib/linux/SHTRIHOLE_JNI.so
g++ -fPIC -shared -I./cpp/src -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux ./cpp/src/linux/SPDRV_JNI.cpp -o ./java/lib/linux/SPDRV_JNI.so
g++ -fPIC -shared -I./cpp/src -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux ./cpp/src/linux/SPOLE_JNI.cpp -o ./java/lib/linux/SPOLE_JNI.so
g++ -fPIC -shared -I./cpp/src -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux ./cpp/src/linux/SPOLE1C_JNI.cpp -o ./java/lib/linux/SPOLE1C_JNI.so
#read -p "Press any key..."
