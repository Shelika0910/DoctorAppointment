@echo off
echo Compiling and running RegisterUser...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" RegisterUser.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" RegisterUser
