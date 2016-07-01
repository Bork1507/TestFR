md bin
rem C:\Progra~1\Java\jdk1.8.0_91\bin\
rem javac -Xstdout compileErrors.txt
echo "Compiling TestFR..."
C:\Progra~2\Java\jdk1.8.0_91\bin\javac -d bin -sourcepath java\src -classpath java\lib\sqlite-jdbc-3.7.2.jar;java\lib\crunchify.jar;java\lib\jssc.jar -encoding "UTF-8" java\src\TestFR.java
echo "Making headers for JNI..."
C:\Progra~2\Java\jdk1.8.0_91\bin\javah -d cpp\src\windows -classpath bin SHTRIHDRV_JNI
echo "Compiling libraries for JNI..."
g++ -shared -Wl,--kill -IC:/Progra~2/Java/jdk1.8.0_91/include -IC:/Progra~2/Java/jdk1.8.0_91/include/win32 -I./cpp/src/windows ./cpp/src/windows/SHTRIHDRV_JNI.cpp -o ./java/lib/windows/SHTRIHDRV_JNI.dll -lole32 -luuid -loleaut32
rem pause
