@echo off
REM Quick build script for GMS-01 Project
REM Run this from the project root directory

echo.
echo ========================================
echo GMS-01 Project - Build & Fix Script
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven from: https://maven.apache.org/download.cgi
    echo.
    pause
    exit /b 1
)

echo Step 1: Cleaning project...
call mvn clean
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven clean failed
    pause
    exit /b 1
)
echo ✓ Clean completed

echo.
echo Step 2: Installing dependencies...
call mvn install -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven install failed
    pause
    exit /b 1
)
echo ✓ Dependencies installed

echo.
echo Step 3: Compiling source code...
call mvn clean compile
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven compile failed
    echo Check the error messages above
    pause
    exit /b 1
)
echo ✓ Compilation successful

echo.
echo ========================================
echo ✓ BUILD SUCCESSFUL!
echo ========================================
echo.
echo Your code is ready to push!
echo.
echo Next steps:
echo   1. git status
echo   2. git add .
echo   3. git commit -m "Your message here"
echo   4. git push origin branch-name
echo.
pause

