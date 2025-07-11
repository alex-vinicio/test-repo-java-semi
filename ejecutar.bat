@echo off
echo ===============================================
echo   Sistema de Tarjetas de Debito - Banco Pichincha
echo   Iniciando aplicacion...
echo ===============================================

if not exist "mvnw.cmd" (
    echo ERROR: Maven Wrapper no encontrado.
    echo Por favor, asegurate de que Maven este instalado.
    echo.
    echo Instrucciones de instalacion:
    echo 1. Descarga Maven desde https://maven.apache.org/download.cgi
    echo 2. Extrae el archivo en C:\Program Files\Apache\maven
    echo 3. Agrega C:\Program Files\Apache\maven\bin al PATH
    echo 4. Reinicia la terminal
    echo.
    pause
    exit /b 1
)

echo Compilando el proyecto...
call mvnw.cmd clean compile

if %ERRORLEVEL% neq 0 (
    echo ERROR: Falló la compilación
    pause
    exit /b 1
)

echo Ejecutando las pruebas...
call mvnw.cmd test

if %ERRORLEVEL% neq 0 (
    echo ERROR: Fallaron las pruebas
    pause
    exit /b 1
)

echo Iniciando la aplicacion...
echo.
echo La aplicacion estara disponible en:
echo   - Aplicacion: http://localhost:8080
echo   - Swagger UI: http://localhost:8080/swagger-ui.html
echo   - H2 Console: http://localhost:8080/h2-console
echo.
echo Presiona Ctrl+C para detener la aplicacion
echo.

call mvnw.cmd spring-boot:run

pause
