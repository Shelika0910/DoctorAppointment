@echo off
echo Compiling and running CheckUsers...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" CheckUsers.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" CheckUsers
