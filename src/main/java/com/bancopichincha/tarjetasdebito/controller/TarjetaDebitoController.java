package com.bancopichincha.tarjetasdebito.controller;

import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoCreateDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoUpdateDTO;
import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import com.bancopichincha.tarjetasdebito.service.TarjetaDebitoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tarjetas-debito")
@Tag(name = "Tarjetas de Débito", description = "API para gestión de tarjetas de débito del Banco Pichincha")
@CrossOrigin(origins = "*")
public class TarjetaDebitoController {

    private static final Logger logger = LoggerFactory.getLogger(TarjetaDebitoController.class);

    @Autowired
    private TarjetaDebitoService tarjetaDebitoService;

    @Operation(summary = "Crear una nueva tarjeta de débito", description = "Crea una nueva tarjeta de débito con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarjeta creada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TarjetaDebitoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Tarjeta ya existe")
    })
    @PostMapping
    public ResponseEntity<TarjetaDebitoDTO> crearTarjeta(
            @Valid @RequestBody TarjetaDebitoCreateDTO tarjetaCreateDTO) {
        logger.info("Solicitud para crear tarjeta de débito: {}", tarjetaCreateDTO);
        
        TarjetaDebitoDTO tarjetaCreada = tarjetaDebitoService.crearTarjeta(tarjetaCreateDTO);
        return new ResponseEntity<>(tarjetaCreada, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todas las tarjetas de débito", description = "Retorna una lista de todas las tarjetas de débito")
    @ApiResponse(responseCode = "200", description = "Lista de tarjetas obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<TarjetaDebitoDTO>> obtenerTodasLasTarjetas() {
        logger.info("Solicitud para obtener todas las tarjetas");
        
        List<TarjetaDebitoDTO> tarjetas = tarjetaDebitoService.obtenerTodasLasTarjetas();
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Obtener tarjeta por ID", description = "Retorna una tarjeta específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDebitoDTO> obtenerTarjetaPorId(
            @Parameter(description = "ID de la tarjeta") @PathVariable Long id) {
        logger.info("Solicitud para obtener tarjeta con ID: {}", id);
        
        Optional<TarjetaDebitoDTO> tarjeta = tarjetaDebitoService.obtenerTarjetaPorId(id);
        return tarjeta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener tarjeta por número", description = "Retorna una tarjeta específica por su número")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @GetMapping("/numero/{numeroTarjeta}")
    public ResponseEntity<TarjetaDebitoDTO> obtenerTarjetaPorNumero(
            @Parameter(description = "Número de la tarjeta") @PathVariable String numeroTarjeta) {
        logger.info("Solicitud para obtener tarjeta con número: {}", numeroTarjeta);
        
        Optional<TarjetaDebitoDTO> tarjeta = tarjetaDebitoService.obtenerTarjetaPorNumero(numeroTarjeta);
        return tarjeta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener tarjetas por cédula", description = "Retorna todas las tarjetas de un titular específico")
    @ApiResponse(responseCode = "200", description = "Lista de tarjetas obtenida exitosamente")
    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<List<TarjetaDebitoDTO>> obtenerTarjetasPorCedula(
            @Parameter(description = "Cédula del titular") @PathVariable String cedula) {
        logger.info("Solicitud para obtener tarjetas con cédula: {}", cedula);
        
        List<TarjetaDebitoDTO> tarjetas = tarjetaDebitoService.obtenerTarjetasPorCedula(cedula);
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Obtener tarjetas por estado", description = "Retorna todas las tarjetas con un estado específico")
    @ApiResponse(responseCode = "200", description = "Lista de tarjetas obtenida exitosamente")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<TarjetaDebitoDTO>> obtenerTarjetasPorEstado(
            @Parameter(description = "Estado de las tarjetas") @PathVariable EstadoTarjeta estado) {
        logger.info("Solicitud para obtener tarjetas con estado: {}", estado);
        
        List<TarjetaDebitoDTO> tarjetas = tarjetaDebitoService.obtenerTarjetasPorEstado(estado);
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Obtener tarjetas por tipo", description = "Retorna todas las tarjetas de un tipo específico")
    @ApiResponse(responseCode = "200", description = "Lista de tarjetas obtenida exitosamente")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<TarjetaDebitoDTO>> obtenerTarjetasPorTipo(
            @Parameter(description = "Tipo de tarjeta") @PathVariable TipoTarjeta tipo) {
        logger.info("Solicitud para obtener tarjetas de tipo: {}", tipo);
        
        List<TarjetaDebitoDTO> tarjetas = tarjetaDebitoService.obtenerTarjetasPorTipo(tipo);
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Buscar tarjetas por nombre", description = "Busca tarjetas por nombre del titular")
    @ApiResponse(responseCode = "200", description = "Lista de tarjetas obtenida exitosamente")
    @GetMapping("/buscar")
    public ResponseEntity<List<TarjetaDebitoDTO>> buscarTarjetasPorNombre(
            @Parameter(description = "Nombre del titular") @RequestParam String nombre) {
        logger.info("Solicitud para buscar tarjetas con nombre: {}", nombre);
        
        List<TarjetaDebitoDTO> tarjetas = tarjetaDebitoService.buscarTarjetasPorNombre(nombre);
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Actualizar una tarjeta", description = "Actualiza los datos de una tarjeta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarjetaDebitoDTO> actualizarTarjeta(
            @Parameter(description = "ID de la tarjeta") @PathVariable Long id,
            @Valid @RequestBody TarjetaDebitoUpdateDTO tarjetaUpdateDTO) {
        logger.info("Solicitud para actualizar tarjeta con ID: {}", id);
        
        Optional<TarjetaDebitoDTO> tarjetaActualizada = tarjetaDebitoService.actualizarTarjeta(id, tarjetaUpdateDTO);
        return tarjetaActualizada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Bloquear una tarjeta", description = "Bloquea una tarjeta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta bloqueada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @PutMapping("/{id}/bloquear")
    public ResponseEntity<TarjetaDebitoDTO> bloquearTarjeta(
            @Parameter(description = "ID de la tarjeta") @PathVariable Long id) {
        logger.info("Solicitud para bloquear tarjeta con ID: {}", id);
        
        Optional<TarjetaDebitoDTO> tarjetaBloqueada = tarjetaDebitoService.bloquearTarjeta(id);
        return tarjetaBloqueada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Desbloquear una tarjeta", description = "Desbloquea una tarjeta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta desbloqueada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @PutMapping("/{id}/desbloquear")
    public ResponseEntity<TarjetaDebitoDTO> desbloquearTarjeta(
            @Parameter(description = "ID de la tarjeta") @PathVariable Long id) {
        logger.info("Solicitud para desbloquear tarjeta con ID: {}", id);
        
        Optional<TarjetaDebitoDTO> tarjetaDesbloqueada = tarjetaDebitoService.desbloquearTarjeta(id);
        return tarjetaDesbloqueada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cancelar una tarjeta", description = "Cancela una tarjeta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta cancelada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<TarjetaDebitoDTO> cancelarTarjeta(
            @Parameter(description = "ID de la tarjeta") @PathVariable Long id) {
        logger.info("Solicitud para cancelar tarjeta con ID: {}", id);
        
        Optional<TarjetaDebitoDTO> tarjetaCancelada = tarjetaDebitoService.cancelarTarjeta(id);
        return tarjetaCancelada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una tarjeta", description = "Elimina una tarjeta específica del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarjeta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarjeta(
            @Parameter(description = "ID de la tarjeta") @PathVariable Long id) {
        logger.info("Solicitud para eliminar tarjeta con ID: {}", id);
        
        boolean eliminada = tarjetaDebitoService.eliminarTarjeta(id);
        return eliminada ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtener tarjetas activas por cédula", description = "Retorna todas las tarjetas activas de un titular")
    @ApiResponse(responseCode = "200", description = "Lista de tarjetas activas obtenida exitosamente")
    @GetMapping("/activas/cedula/{cedula}")
    public ResponseEntity<List<TarjetaDebitoDTO>> obtenerTarjetasActivasPorCedula(
            @Parameter(description = "Cédula del titular") @PathVariable String cedula) {
        logger.info("Solicitud para obtener tarjetas activas con cédula: {}", cedula);
        
        List<TarjetaDebitoDTO> tarjetas = tarjetaDebitoService.obtenerTarjetasActivasPorCedula(cedula);
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Obtener tarjetas próximas a vencer", description = "Retorna tarjetas que vencen en los próximos 30 días")
    @ApiResponse(responseCode = "200", description = "Lista de tarjetas próximas a vencer obtenida exitosamente")
    @GetMapping("/proximas-vencer")
    public ResponseEntity<List<TarjetaDebitoDTO>> obtenerTarjetasProximasAVencer() {
        logger.info("Solicitud para obtener tarjetas próximas a vencer");
        
        List<TarjetaDebitoDTO> tarjetas = tarjetaDebitoService.obtenerTarjetasProximasAVencer();
        return ResponseEntity.ok(tarjetas);
    }

    @Operation(summary = "Contar tarjetas por estado", description = "Retorna el número de tarjetas por estado")
    @ApiResponse(responseCode = "200", description = "Conteo obtenido exitosamente")
    @GetMapping("/contar/estado/{estado}")
    public ResponseEntity<Long> contarTarjetasPorEstado(
            @Parameter(description = "Estado de las tarjetas") @PathVariable EstadoTarjeta estado) {
        logger.info("Solicitud para contar tarjetas con estado: {}", estado);
        
        Long cantidad = tarjetaDebitoService.contarTarjetasPorEstado(estado);
        return ResponseEntity.ok(cantidad);
    }

    @Operation(summary = "Actualizar tarjetas vencidas", description = "Actualiza el estado de las tarjetas vencidas")
    @ApiResponse(responseCode = "200", description = "Tarjetas vencidas actualizadas exitosamente")
    @PutMapping("/actualizar-vencidas")
    public ResponseEntity<String> actualizarTarjetasVencidas() {
        logger.info("Solicitud para actualizar tarjetas vencidas");
        
        tarjetaDebitoService.actualizarTarjetasVencidas();
        return ResponseEntity.ok("Tarjetas vencidas actualizadas exitosamente");
    }
}
