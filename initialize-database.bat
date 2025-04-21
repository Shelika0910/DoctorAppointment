@echo off
echo Compiling and running InitializeDatabase...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" InitializeDatabase.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" InitializeDatabase
