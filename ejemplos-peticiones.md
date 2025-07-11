# Ejemplos de Peticiones HTTP - Sistema de Tarjetas de Débito

Este archivo contiene ejemplos de peticiones HTTP para probar todas las funcionalidades del sistema.

## 1. Crear una nueva tarjeta de débito

### POST /api/v1/tarjetas-debito
```http
POST http://localhost:8080/api/v1/tarjetas-debito
Content-Type: application/json

{
    "nombreTitular": "Pedro Sánchez García",
    "cedula": "1122334455",
    "limiteDiario": 1500.00,
    "saldoInicial": 1000.00,
    "tipoTarjeta": "GOLD",
    "telefono": "0987654321",
    "email": "pedro.sanchez@email.com"
}
```

## 2. Obtener todas las tarjetas

### GET /api/v1/tarjetas-debito
```http
GET http://localhost:8080/api/v1/tarjetas-debito
```

## 3. Obtener tarjeta por ID

### GET /api/v1/tarjetas-debito/{id}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/1
```

## 4. Obtener tarjeta por número

### GET /api/v1/tarjetas-debito/numero/{numeroTarjeta}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/numero/5428123456789012
```

## 5. Obtener tarjetas por cédula

### GET /api/v1/tarjetas-debito/cedula/{cedula}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/cedula/1234567890
```

## 6. Obtener tarjetas por estado

### GET /api/v1/tarjetas-debito/estado/{estado}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/estado/ACTIVA
```

Valores válidos para estado:
- ACTIVA
- BLOQUEADA
- CANCELADA
- VENCIDA
- SUSPENDIDA

## 7. Obtener tarjetas por tipo

### GET /api/v1/tarjetas-debito/tipo/{tipo}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/tipo/GOLD
```

Valores válidos para tipo:
- CLASICA
- GOLD
- PLATINUM
- SIGNATURE
- EMPRESARIAL

## 8. Buscar tarjetas por nombre

### GET /api/v1/tarjetas-debito/buscar?nombre={nombre}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/buscar?nombre=Juan
```

## 9. Actualizar una tarjeta

### PUT /api/v1/tarjetas-debito/{id}
```http
PUT http://localhost:8080/api/v1/tarjetas-debito/1
Content-Type: application/json

{
    "limiteDiario": 2000.00,
    "telefono": "0987654999",
    "email": "nuevo.email@email.com"
}
```

## 10. Bloquear una tarjeta

### PUT /api/v1/tarjetas-debito/{id}/bloquear
```http
PUT http://localhost:8080/api/v1/tarjetas-debito/1/bloquear
```

## 11. Desbloquear una tarjeta

### PUT /api/v1/tarjetas-debito/{id}/desbloquear
```http
PUT http://localhost:8080/api/v1/tarjetas-debito/1/desbloquear
```

## 12. Cancelar una tarjeta

### PUT /api/v1/tarjetas-debito/{id}/cancelar
```http
PUT http://localhost:8080/api/v1/tarjetas-debito/1/cancelar
```

## 13. Obtener tarjetas activas por cédula

### GET /api/v1/tarjetas-debito/activas/cedula/{cedula}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/activas/cedula/1234567890
```

## 14. Obtener tarjetas próximas a vencer

### GET /api/v1/tarjetas-debito/proximas-vencer
```http
GET http://localhost:8080/api/v1/tarjetas-debito/proximas-vencer
```

## 15. Contar tarjetas por estado

### GET /api/v1/tarjetas-debito/contar/estado/{estado}
```http
GET http://localhost:8080/api/v1/tarjetas-debito/contar/estado/ACTIVA
```

## 16. Actualizar tarjetas vencidas

### PUT /api/v1/tarjetas-debito/actualizar-vencidas
```http
PUT http://localhost:8080/api/v1/tarjetas-debito/actualizar-vencidas
```

## 17. Eliminar una tarjeta

### DELETE /api/v1/tarjetas-debito/{id}
```http
DELETE http://localhost:8080/api/v1/tarjetas-debito/1
```

## Ejemplos de Respuestas

### Respuesta exitosa de creación (201 Created)
```json
{
    "id": 11,
    "numeroTarjeta": "5428901234567890",
    "nombreTitular": "Pedro Sánchez García",
    "cedula": "1122334455",
    "fechaExpiracion": "2030-07-11",
    "cvv": "123",
    "limiteDiario": 1500.00,
    "saldoDisponible": 1000.00,
    "estado": "ACTIVA",
    "tipoTarjeta": "GOLD",
    "fechaCreacion": "2025-07-11 10:30:00",
    "fechaActualizacion": "2025-07-11 10:30:00",
    "telefono": "0987654321",
    "email": "pedro.sanchez@email.com"
}
```

### Respuesta de error de validación (400 Bad Request)
```json
{
    "status": 400,
    "message": "Error de validación",
    "timestamp": "2025-07-11T10:30:00",
    "errors": {
        "nombreTitular": "El nombre del titular es obligatorio",
        "cedula": "La cédula debe tener exactamente 10 dígitos",
        "limiteDiario": "El límite diario debe ser mayor que 0"
    }
}
```

### Respuesta de recurso no encontrado (404 Not Found)
```json
{
    "status": 404,
    "message": "Tarjeta con ID 999 no encontrada",
    "timestamp": "2025-07-11T10:30:00"
}
```

### Respuesta de error de negocio (400 Bad Request)
```json
{
    "status": 400,
    "message": "Ya existe una tarjeta activa para la cédula: 1234567890",
    "timestamp": "2025-07-11T10:30:00"
}
```

## Consejos para Pruebas

1. **Usar herramientas como Postman o Thunder Client** para enviar las peticiones
2. **Verificar los datos de prueba** cargados automáticamente al iniciar la aplicación
3. **Probar los diferentes estados** y tipos de tarjetas
4. **Validar las respuestas de error** con datos inválidos
5. **Usar la documentación Swagger** en http://localhost:8080/swagger-ui.html

## Datos de Prueba Disponibles

La aplicación incluye 10 tarjetas de prueba:
- IDs del 1 al 10
- Diferentes tipos (CLASICA, GOLD, PLATINUM, SIGNATURE, EMPRESARIAL)
- Diferentes estados (ACTIVA, BLOQUEADA, SUSPENDIDA)
- Una tarjeta próxima a vencer (ID 8)

## Secuencia de Pruebas Recomendada

1. Obtener todas las tarjetas para ver los datos iniciales
2. Crear una nueva tarjeta
3. Buscar tarjetas por diferentes criterios
4. Actualizar una tarjeta existente
5. Cambiar estados (bloquear/desbloquear)
6. Probar funcionalidades especiales (próximas a vencer, estadísticas)
7. Eliminar una tarjeta de prueba
