@echo off
REM ----------------------------------------
REM Run Pahana Edu Billing System with Java 17
REM ----------------------------------------

REM Set Java 17 for this session
set "JAVA_HOME=C:\Users\navod_09089\AppData\Local\Programs\Eclipse Adoptium\jdk-17.0.16.8-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Go to project folder
cd /d "E:\OneDrive - Dialog Axiata PLC\ICBT\Advance Programming\pahana-edu-bookshop-v1\pahana-edu-bookshop-v1\billing-system"

REM Clean and build the project
mvn clean install

REM Run Spring Boot
mvn spring-boot:run

REM Keep terminal open
pause
