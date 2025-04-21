@echo off
echo Checking MySQL server status...
mysql --host=localhost --user=root --password= -e "SELECT 'MySQL is running!' AS Status;"
if %ERRORLEVEL% EQU 0 (
    echo MySQL server is running.
) else (
    echo MySQL server is not running or there is a connection issue.
    echo Please make sure MySQL server is running and the credentials are correct.
)
