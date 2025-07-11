@echo off
echo ===============================================
echo   Sistema de Tarjetas de Debito - Banco Pichincha
echo   Compilando proyecto...
echo ===============================================

if not exist "mvnw.cmd" (
    echo ERROR: Maven Wrapper no encontrado.
    echo.
    echo Este proyecto requiere Maven para compilar.
    echo.
    echo Opciones:
    echo 1. Instalar Maven:
    echo    - Descarga desde https://maven.apache.org/download.cgi
    echo    - Extrae en C:\Program Files\Apache\maven
    echo    - Agrega C:\Program Files\Apache\maven\bin al PATH
    echo.
    echo 2. Usar IDE como IntelliJ IDEA o Eclipse con soporte Maven
    echo.
    echo 3. Usar Spring Boot desde línea de comandos con Java:
    echo    java -jar target/tarjetas-debito-1.0.0.jar
    echo.
    pause
    exit /b 1
)

echo Limpiando proyecto anterior...
call mvnw.cmd clean

if %ERRORLEVEL% neq 0 (
    echo ERROR: Falló la limpieza del proyecto
    pause
    exit /b 1
)

echo Compilando el proyecto...
call mvnw.cmd compile

if %ERRORLEVEL% neq 0 (
    echo ERROR: Falló la compilación
    pause
    exit /b 1
)

echo ===============================================
echo   Compilación exitosa!
echo ===============================================
echo.
echo Proyecto compilado correctamente.
echo.
echo Proximos pasos:
echo 1. Ejecutar pruebas: mvnw test
echo 2. Iniciar aplicacion: mvnw spring-boot:run
echo 3. O usar el script: ejecutar.bat
echo.
pause
