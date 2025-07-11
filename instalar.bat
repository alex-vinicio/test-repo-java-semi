@echo off
echo ===============================================
echo   Sistema de Tarjetas de Debito - Banco Pichincha
echo   Script de Instalacion y Configuracion
echo ===============================================
echo.

REM Verificar Java
echo Verificando instalacion de Java...
java -version > nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo ERROR: Java no está instalado o no está en el PATH
    echo.
    echo Por favor, instala Java 17 o superior:
    echo 1. Descarga Java desde https://www.oracle.com/java/technologies/downloads/
    echo 2. Instala Java JDK 17 o superior
    echo 3. Configura JAVA_HOME en las variables de entorno
    echo 4. Agrega JAVA_HOME\bin al PATH
    echo 5. Reinicia la terminal
    echo.
    pause
    exit /b 1
)

echo Java encontrado:
java -version

echo.
echo Verificando directorio del proyecto...
if not exist "pom.xml" (
    echo ERROR: No se encontró el archivo pom.xml
    echo Por favor, ejecuta este script desde la raíz del proyecto
    pause
    exit /b 1
)

echo.
echo Descargando Maven Wrapper...
if not exist ".mvn\wrapper\maven-wrapper.jar" (
    echo Descargando maven-wrapper.jar...
    powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar' -OutFile '.mvn\wrapper\maven-wrapper.jar'}"
    
    if %ERRORLEVEL% neq 0 (
        echo ERROR: No se pudo descargar maven-wrapper.jar
        echo Por favor, verifica tu conexión a internet
        pause
        exit /b 1
    )
    echo maven-wrapper.jar descargado exitosamente
) else (
    echo maven-wrapper.jar ya existe
)

echo.
echo Compilando el proyecto...
call mvnw.cmd clean compile

if %ERRORLEVEL% neq 0 (
    echo ERROR: Falló la compilación del proyecto
    echo Por favor, revisa los errores anteriores
    pause
    exit /b 1
)

echo.
echo Ejecutando pruebas...
call mvnw.cmd test

if %ERRORLEVEL% neq 0 (
    echo ADVERTENCIA: Algunas pruebas fallaron
    echo El proyecto se puede ejecutar, pero revisa los errores
    pause
)

echo.
echo ===============================================
echo   Instalacion completada exitosamente!
echo ===============================================
echo.
echo El proyecto está listo para ejecutarse.
echo.
echo Comandos disponibles:
echo   - Ejecutar aplicacion: mvnw spring-boot:run
echo   - Ejecutar pruebas: mvnw test
echo   - Compilar proyecto: mvnw compile
echo   - Limpiar proyecto: mvnw clean
echo.
echo Scripts disponibles:
echo   - ejecutar.bat: Inicia la aplicación
echo   - compilar.bat: Compila el proyecto
echo.
echo URLs importantes:
echo   - Aplicacion: http://localhost:8080
echo   - Swagger UI: http://localhost:8080/swagger-ui.html
echo   - H2 Console: http://localhost:8080/h2-console
echo.
echo ¿Deseas ejecutar la aplicación ahora? (S/N)
set /p ejecutar=

if /i "%ejecutar%"=="S" (
    echo.
    echo Iniciando aplicación...
    call mvnw.cmd spring-boot:run
)

echo.
echo ¡Gracias por usar el Sistema de Tarjetas de Débito!
pause
