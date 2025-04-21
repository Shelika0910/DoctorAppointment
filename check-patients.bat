@echo off
echo Compiling and running CheckPatients...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" CheckPatients.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" CheckPatients
