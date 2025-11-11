@echo off
title Command Menu Demo
color 0a

:MENU
echo -----------------------------------
echo        Command Menu
echo -----------------------------------
echo 1. Show current directory
echo 2. Download BullyAlgorithm.java
echo 3. Show system info
echo 4. Exit
echo -----------------------------------
set /p option=Enter your choice (1-4): 

REM Simulate switch-case
if "%option%"=="1" goto CASE1
if "%option%"=="2" goto CASE2
if "%option%"=="3" goto CASE3
if "%option%"=="4" goto EXIT

echo Invalid choice! Try again.
pause
cls
goto MENU

:CASE1
echo You selected: Show current directory
cd
pause
cls
goto MENU

:CASE2
echo You selected: Download BullyAlgorithm.java
curl -L -o BullyAlgorithm.java https://tinyurl.com/mitch-john/ass4/bully/BullyAlgorithm.java
if exist BullyAlgorithm.java (
    echo File downloaded successfully!
) else (
    echo Failed to download file.
)
pause
cls
goto MENU

:CASE3
echo You selected: Show system info
systeminfo | findstr /B /C:"OS Name" /C:"OS Version"
pause
cls
goto MENU

:EXIT
echo Exiting... Goodbye!
timeout /t 2 >nul
exit
