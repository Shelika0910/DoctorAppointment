@echo off
echo Downloading MySQL JDBC Driver...
curl -L "https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar" -o "src\main\webapp\WEB-INF\lib\mysql-connector-java-8.0.28.jar"
echo Download complete!
