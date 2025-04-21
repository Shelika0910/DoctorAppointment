@echo off
echo Compiling and running TestDBConnection...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar;src" src\main\java\com\doctorapp\util\TestDBConnection.java
java -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar;src" com.doctorapp.util.TestDBConnection
