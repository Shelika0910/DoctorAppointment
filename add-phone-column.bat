@echo off
echo Compiling and running AddPhoneColumn...
javac -cp "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" AddPhoneColumn.java
java -cp ".;src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar" AddPhoneColumn
