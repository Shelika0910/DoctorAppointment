@echo off
echo Compiling and running CheckTables...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" CheckTables.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" CheckTables
