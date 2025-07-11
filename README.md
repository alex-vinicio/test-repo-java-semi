# Sistema de Gestión de Tarjetas de Débito - Banco Pichincha

## Descripción del Proyecto

Este proyecto es un sistema de gestión de tarjetas de débito desarrollado para el Banco Pichincha, implementado con Spring Boot siguiendo el patrón MVC (Modelo-Vista-Controlador). El sistema permite realizar operaciones CRUD completas sobre tarjetas de débito, incluyendo creación, consulta, actualización y eliminación de tarjetas.

## Arquitectura del Proyecto

### Patrón MVC Implementado

- **Modelo (Model)**: Entidades JPA y DTOs para representar los datos
- **Vista (View)**: API REST que expone endpoints JSON
- **Controlador (Controller)**: Controladores REST que manejan las peticiones HTTP

### Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/bancopichincha/tarjetasdebito/
│   │       ├── TarjetasDebitoApplication.java
│   │       ├── config/
│   │       │   ├── ApplicationConfig.java
│   │       │   ├── DataInitializer.java
│   │       │   └── SwaggerConfig.java
│   │       ├── controller/
│   │       │   └── TarjetaDebitoController.java
│   │       ├── exception/
│   │       │   ├── BusinessException.java
│   │       │   ├── GlobalExceptionHandler.java
│   │       │   └── ResourceNotFoundException.java
│   │       ├── model/
│   │       │   ├── dto/
│   │       │   │   ├── TarjetaDebitoCreateDTO.java
│   │       │   │   ├── TarjetaDebitoDTO.java
│   │       │   │   └── TarjetaDebitoUpdateDTO.java
│   │       │   └── entity/
│   │       │       ├── EstadoTarjeta.java
│   │       │       ├── TarjetaDebito.java
│   │       │       └── TipoTarjeta.java
│   │       ├── repository/
│   │       │   └── TarjetaDebitoRepository.java
│   │       ├── service/
│   │       │   ├── impl/
│   │       │   │   └── TarjetaDebitoServiceImpl.java
│   │       │   └── TarjetaDebitoService.java
│   │       └── util/
│   │           ├── TarjetaDebitoMapper.java
│   │           └── TarjetaNumberGenerator.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/bancopichincha/tarjetasdebito/
            ├── controller/
            │   └── TarjetaDebitoControllerTest.java
            └── service/
                └── impl/
                    └── TarjetaDebitoServiceImplTest.java
```

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal
- **Spring Boot 3.2.0**: Framework principal
- **Spring Data JPA**: Para persistencia de datos
- **Spring Web**: Para crear APIs REST
- **Spring Validation**: Para validación de datos
- **H2 Database**: Base de datos en memoria para desarrollo
- **Maven**: Gestor de dependencias
- **Swagger/OpenAPI**: Documentación automática de APIs
- **ModelMapper**: Mapeo entre entidades y DTOs
- **JUnit 5**: Framework de pruebas
- **Mockito**: Framework de mocking para pruebas

## Funcionalidades Principales

### Operaciones CRUD

1. **Crear Tarjeta de Débito**
   - Endpoint: `POST /api/v1/tarjetas-debito`
   - Genera automáticamente número de tarjeta y CVV
   - Valida datos del titular

2. **Consultar Tarjetas**
   - Obtener todas: `GET /api/v1/tarjetas-debito`
   - Por ID: `GET /api/v1/tarjetas-debito/{id}`
   - Por número: `GET /api/v1/tarjetas-debito/numero/{numeroTarjeta}`
   - Por cédula: `GET /api/v1/tarjetas-debito/cedula/{cedula}`
   - Por estado: `GET /api/v1/tarjetas-debito/estado/{estado}`
   - Por tipo: `GET /api/v1/tarjetas-debito/tipo/{tipo}`

3. **Actualizar Tarjeta**
   - Endpoint: `PUT /api/v1/tarjetas-debito/{id}`
   - Actualización parcial de campos

4. **Gestión de Estados**
   - Bloquear: `PUT /api/v1/tarjetas-debito/{id}/bloquear`
   - Desbloquear: `PUT /api/v1/tarjetas-debito/{id}/desbloquear`
   - Cancelar: `PUT /api/v1/tarjetas-debito/{id}/cancelar`

5. **Eliminar Tarjeta**
   - Endpoint: `DELETE /api/v1/tarjetas-debito/{id}`

### Funcionalidades Adicionales

- **Búsqueda por nombre**: `GET /api/v1/tarjetas-debito/buscar?nombre={nombre}`
- **Tarjetas próximas a vencer**: `GET /api/v1/tarjetas-debito/proximas-vencer`
- **Estadísticas**: `GET /api/v1/tarjetas-debito/contar/estado/{estado}`
- **Actualización automática de vencidas**: `PUT /api/v1/tarjetas-debito/actualizar-vencidas`

## Tipos de Tarjetas

- **CLASICA**: Tarjeta básica con límites estándar
- **GOLD**: Tarjeta premium con beneficios adicionales
- **PLATINUM**: Tarjeta de alto rango con límites elevados
- **SIGNATURE**: Tarjeta exclusiva con servicios especiales
- **EMPRESARIAL**: Tarjeta para uso corporativo

## Estados de Tarjetas

- **ACTIVA**: Tarjeta operativa y funcional
- **BLOQUEADA**: Tarjeta temporalmente bloqueada
- **CANCELADA**: Tarjeta cancelada permanentemente
- **VENCIDA**: Tarjeta que ha superado su fecha de expiración
- **SUSPENDIDA**: Tarjeta suspendida por motivos administrativos

## Instalación y Ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior

### Pasos para ejecutar

1. **Clonar o descargar el proyecto**
   ```bash
   cd c:\Alex\hackaton-2025\Fase2\java
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar las pruebas**
   ```bash
   mvn test
   ```

4. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en: `http://localhost:8080`

## Documentación de la API

### Swagger UI
Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva en:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/api-docs`

### Consola H2
Para explorar la base de datos en memoria:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: (vacía)

## Datos de Prueba

La aplicación incluye datos de prueba que se cargan automáticamente al iniciar:

- **10 tarjetas de débito** con diferentes tipos y estados
- **Diferentes titulares** con información completa
- **Variedad de estados** para probar todas las funcionalidades
- **Tarjetas próximas a vencer** para probar alertas

## Ejemplos de Uso

### Crear una nueva tarjeta
```bash
curl -X POST http://localhost:8080/api/v1/tarjetas-debito \
  -H "Content-Type: application/json" \
  -d '{
    "nombreTitular": "Pedro Sánchez",
    "cedula": "1122334455",
    "limiteDiario": 1500.00,
    "saldoInicial": 1000.00,
    "tipoTarjeta": "GOLD",
    "telefono": "0987654321",
    "email": "pedro.sanchez@email.com"
  }'
```

### Obtener todas las tarjetas
```bash
curl -X GET http://localhost:8080/api/v1/tarjetas-debito
```

### Bloquear una tarjeta
```bash
curl -X PUT http://localhost:8080/api/v1/tarjetas-debito/1/bloquear
```

## Validaciones Implementadas

- **Número de tarjeta**: 16 dígitos numéricos
- **Cédula**: 10 dígitos numéricos
- **CVV**: 3 dígitos numéricos
- **Nombre del titular**: 2-100 caracteres
- **Email**: Formato válido de email
- **Teléfono**: 10 dígitos numéricos
- **Límite diario**: Mayor que 0
- **Saldo**: Mayor o igual a 0

## Manejo de Errores

- **404 Not Found**: Recurso no encontrado
- **400 Bad Request**: Datos inválidos o errores de validación
- **409 Conflict**: Conflictos de negocio (ej. tarjeta duplicada)
- **500 Internal Server Error**: Errores internos del servidor

## Logging

El proyecto incluye logging detallado para:
- Operaciones CRUD
- Errores y excepciones
- Inicialización de datos
- Validaciones de negocio

## Consideraciones de Seguridad

- **Validación de entrada**: Todos los datos se validan antes del procesamiento
- **Manejo de excepciones**: Errores controlados sin exposición de información sensible
- **Generación segura**: Uso de `SecureRandom` para generar números de tarjeta y CVV

## Autor

Desarrollado como proyecto para el Banco Pichincha - Sistema de Gestión de Tarjetas de Débito

---

**Nota**: Este es un proyecto de demostración con datos mockeados. No debe usarse en producción sin las debidas consideraciones de seguridad y validaciones adicionales.
