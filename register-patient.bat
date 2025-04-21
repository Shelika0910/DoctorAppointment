@echo off
echo Compiling and running RegisterPatient...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" RegisterPatient.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" RegisterPatient
