@echo off
echo Compiling and running TestDB...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" TestDB.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" TestDB
