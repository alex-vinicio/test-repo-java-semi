# 🏦 Sistema de Gestión de Tarjetas de Débito - Banco Pichincha

## 📋 Resumen del Proyecto

**Desarrollador**: Desarrollador Semi-Senior Java  
**Cliente**: Banco Pichincha  
**Tecnología**: Java 17 + Spring Boot 3.2.0  
**Arquitectura**: MVC (Modelo-Vista-Controlador)  
**Fecha**: Julio 2025  

## 🎯 Objetivo

Crear un sistema completo de gestión de tarjetas de débito que permita al Banco Pichincha administrar eficientemente las tarjetas de sus clientes, con operaciones CRUD completas y funcionalidades avanzadas.

## 🏗️ Arquitectura del Sistema

### Patrón MVC Implementado

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   CONTROLADOR   │    │     SERVICIO    │    │     MODELO      │
│                 │    │                 │    │                 │
│ • REST APIs     │◄──►│ • Lógica de     │◄──►│ • Entidades JPA │
│ • Validaciones  │    │   Negocio       │    │ • DTOs          │
│ • Mapeo URLs    │    │ • Transacciones │    │ • Repositorios  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Capas del Sistema

1. **Capa de Presentación** (`controller/`)
   - Controladores REST
   - Manejo de peticiones HTTP
   - Validación de entrada
   - Serialización JSON

2. **Capa de Servicio** (`service/`)
   - Lógica de negocio
   - Validaciones de dominio
   - Gestión de transacciones
   - Orquestación de operaciones

3. **Capa de Persistencia** (`repository/`)
   - Acceso a datos
   - Consultas personalizadas
   - Repositorios JPA

4. **Capa de Modelo** (`model/`)
   - Entidades de dominio
   - DTOs para transferencia
   - Enums y constantes

## 🚀 Funcionalidades Principales

### ✅ Operaciones CRUD Completas

| Operación | Endpoint | Método | Descripción |
|-----------|----------|---------|-------------|
| **Crear** | `/api/v1/tarjetas-debito` | POST | Crear nueva tarjeta |
| **Leer** | `/api/v1/tarjetas-debito` | GET | Obtener todas las tarjetas |
| **Leer** | `/api/v1/tarjetas-debito/{id}` | GET | Obtener tarjeta por ID |
| **Actualizar** | `/api/v1/tarjetas-debito/{id}` | PUT | Actualizar tarjeta |
| **Eliminar** | `/api/v1/tarjetas-debito/{id}` | DELETE | Eliminar tarjeta |

### 🔍 Funcionalidades de Búsqueda

- **Por número de tarjeta**: Búsqueda exacta
- **Por cédula**: Todas las tarjetas de un cliente
- **Por estado**: Filtrar por estado (ACTIVA, BLOQUEADA, etc.)
- **Por tipo**: Filtrar por tipo (CLASICA, GOLD, PLATINUM, etc.)
- **Por nombre**: Búsqueda parcial por nombre del titular

### 🔒 Gestión de Estados

- **Bloquear tarjeta**: Suspender temporalmente
- **Desbloquear tarjeta**: Reactivar tarjeta bloqueada
- **Cancelar tarjeta**: Cancelación permanente
- **Actualizar vencidas**: Proceso automático

### 📊 Funcionalidades Avanzadas

- **Alertas de vencimiento**: Tarjetas próximas a vencer
- **Estadísticas**: Conteo por estado y tipo
- **Validaciones de negocio**: Reglas específicas del banco
- **Generación automática**: Números de tarjeta y CVV

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 17**: Lenguaje de programación
- **Spring Boot 3.2.0**: Framework principal
- **Spring Data JPA**: ORM y acceso a datos
- **Spring Web**: APIs REST
- **Spring Validation**: Validaciones

### Base de Datos
- **H2 Database**: Base de datos en memoria
- **Hibernate**: ORM para Java

### Documentación
- **OpenAPI 3**: Especificación de APIs
- **Swagger UI**: Interfaz de documentación

### Testing
- **JUnit 5**: Framework de pruebas
- **Mockito**: Mocking para pruebas unitarias
- **Spring Boot Test**: Pruebas de integración

### Utilidades
- **ModelMapper**: Mapeo entre DTOs y entidades
- **Maven**: Gestión de dependencias

## 📦 Estructura del Proyecto

```
tarjetas-debito/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.bancopichincha.tarjetasdebito/
│   │   │       ├── config/           # Configuraciones
│   │   │       ├── controller/       # Controladores REST
│   │   │       ├── exception/        # Manejo de errores
│   │   │       ├── model/           # Modelos de datos
│   │   │       │   ├── dto/         # Data Transfer Objects
│   │   │       │   └── entity/      # Entidades JPA
│   │   │       ├── repository/      # Repositorios de datos
│   │   │       ├── service/         # Lógica de negocio
│   │   │       └── util/            # Utilidades
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Pruebas unitarias
├── pom.xml                          # Configuración Maven
├── README.md                        # Documentación
└── ejemplos-peticiones.md           # Ejemplos de uso
```

## 📊 Modelo de Datos

### Entidad Principal: TarjetaDebito

```java
@Entity
public class TarjetaDebito {
    private Long id;                    // ID único
    private String numeroTarjeta;       // 16 dígitos
    private String nombreTitular;       // Nombre completo
    private String cedula;              // 10 dígitos
    private LocalDate fechaExpiracion;  // Fecha de vencimiento
    private String cvv;                 // 3 dígitos
    private BigDecimal limiteDiario;    // Límite diario
    private BigDecimal saldoDisponible; // Saldo actual
    private EstadoTarjeta estado;       // Estado actual
    private TipoTarjeta tipoTarjeta;    // Tipo de tarjeta
    private String telefono;            // Teléfono contacto
    private String email;               // Email contacto
    // + timestamps y metadatos
}
```

### Enums del Sistema

**EstadoTarjeta**:
- `ACTIVA`: Operativa
- `BLOQUEADA`: Temporalmente bloqueada
- `CANCELADA`: Cancelada permanentemente
- `VENCIDA`: Fecha expirada
- `SUSPENDIDA`: Suspendida administrativamente

**TipoTarjeta**:
- `CLASICA`: Tarjeta básica
- `GOLD`: Tarjeta premium
- `PLATINUM`: Tarjeta de lujo
- `SIGNATURE`: Tarjeta exclusiva
- `EMPRESARIAL`: Tarjeta corporativa

## 🔧 Configuración y Ejecución

### Requisitos Previos
- Java 17 o superior
- Maven 3.6+ (o usar Maven Wrapper incluido)

### Instalación Rápida

1. **Clonar/Descargar el proyecto**
   ```bash
   cd c:\Alex\hackaton-2025\Fase2\java
   ```

2. **Ejecutar script de instalación**
   ```cmd
   instalar.bat
   ```

3. **Iniciar aplicación**
   ```cmd
   ejecutar.bat
   ```

### Ejecución Manual

```bash
# Compilar
mvnw clean compile

# Ejecutar pruebas
mvnw test

# Iniciar aplicación
mvnw spring-boot:run
```

## 🌐 Endpoints Principales

### Aplicación
- **Aplicación**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console

### APIs REST
- **Base URL**: http://localhost:8080/api/v1/tarjetas-debito
- **Documentación**: http://localhost:8080/api-docs

## 📁 Datos de Prueba

El sistema incluye **10 tarjetas de prueba** que se cargan automáticamente:

- **Diferentes tipos**: Desde CLASICA hasta EMPRESARIAL
- **Diferentes estados**: ACTIVA, BLOQUEADA, SUSPENDIDA
- **Datos realistas**: Nombres, cédulas, teléfonos, emails
- **Casos especiales**: Tarjetas próximas a vencer

## 🧪 Pruebas Incluidas

- **Pruebas unitarias**: Servicios y controladores
- **Pruebas de integración**: APIs REST
- **Cobertura**: Casos positivos y negativos
- **Validaciones**: Datos inválidos y errores

## 🔒 Seguridad y Validaciones

### Validaciones de Entrada
- Número de tarjeta: 16 dígitos
- Cédula: 10 dígitos
- CVV: 3 dígitos
- Email: Formato válido
- Teléfono: 10 dígitos
- Límites: Valores positivos

### Validaciones de Negocio
- Una tarjeta activa por cédula
- Estados válidos para transiciones
- Fechas de expiración futuras
- Límites coherentes

## 📈 Características Destacadas

### 🏆 Buenas Prácticas
- **Patrón MVC**: Separación clara de responsabilidades
- **DTOs**: Transferencia segura de datos
- **Validaciones**: Múltiples niveles de validación
- **Manejo de errores**: Respuestas consistentes
- **Logging**: Trazabilidad completa

### 🚀 Escalabilidad
- **Modular**: Fácil de extender
- **Configurable**: Múltiples entornos
- **Documentado**: APIs autodocumentadas
- **Testeable**: Pruebas automatizadas

### 🔧 Mantenibilidad
- **Código limpio**: Nomenclatura clara
- **Documentación**: Código comentado
- **Estándares**: Convenciones Java
- **Configuración**: Externalizadas

## 📝 Próximos Pasos

### Posibles Mejoras
1. **Autenticación**: JWT, OAuth2
2. **Base de datos**: PostgreSQL, MySQL
3. **Caching**: Redis, Hazelcast
4. **Monitoring**: Actuator, Micrometer
5. **Frontend**: React, Angular, Vue

### Funcionalidades Futuras
1. **Transacciones**: Historial de movimientos
2. **Notificaciones**: Email, SMS
3. **Reporting**: Reportes y estadísticas
4. **Integración**: Servicios externos
5. **Mobile**: APIs para móviles

## 👨‍💻 Sobre el Desarrollo

Este proyecto fue desarrollado siguiendo las mejores prácticas de desarrollo Java y Spring Boot, con enfoque en:

- **Calidad del código**: Limpio, legible y mantenible
- **Arquitectura sólida**: Patrón MVC bien implementado
- **Documentación completa**: APIs y código documentados
- **Pruebas exhaustivas**: Cobertura de casos principales
- **Experiencia del desarrollador**: Fácil de entender y extender

---

**Desarrollado con ❤️ para el Banco Pichincha**
