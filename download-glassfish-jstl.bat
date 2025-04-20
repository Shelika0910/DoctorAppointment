@echo off
echo Downloading Glassfish JSTL Implementation...
mkdir -p src\main\webapp\WEB-INF\lib
curl -L "https://repo1.maven.org/maven2/org/glassfish/web/jakarta.servlet.jsp.jstl/3.0.1/jakarta.servlet.jsp.jstl-3.0.1.jar" -o "src\main\webapp\WEB-INF\lib\jakarta.servlet.jsp.jstl-3.0.1.jar"
curl -L "https://repo1.maven.org/maven2/jakarta/servlet/jsp/jstl/jakarta.servlet.jsp.jstl-api/3.0.0/jakarta.servlet.jsp.jstl-api-3.0.0.jar" -o "src\main\webapp\WEB-INF\lib\jakarta.servlet.jsp.jstl-api-3.0.0.jar"
echo Download complete!
