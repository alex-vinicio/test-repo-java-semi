package com.bancopichincha.tarjetasdebito.controller;

import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoCreateDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoDTO;
import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import com.bancopichincha.tarjetasdebito.service.TarjetaDebitoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TarjetaDebitoController.class)
public class TarjetaDebitoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarjetaDebitoService tarjetaDebitoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearTarjeta_Exitoso() throws Exception {
        // Arrange
        TarjetaDebitoCreateDTO createDTO = new TarjetaDebitoCreateDTO();
        createDTO.setNombreTitular("Juan Pérez");
        createDTO.setCedula("1234567890");
        createDTO.setLimiteDiario(new BigDecimal("1000.00"));
        createDTO.setSaldoInicial(new BigDecimal("500.00"));
        createDTO.setTipoTarjeta(TipoTarjeta.CLASICA);

        TarjetaDebitoDTO responseDTO = new TarjetaDebitoDTO();
        responseDTO.setId(1L);
        responseDTO.setNumeroTarjeta("5428123456789012");
        responseDTO.setNombreTitular("Juan Pérez");
        responseDTO.setCedula("1234567890");
        responseDTO.setLimiteDiario(new BigDecimal("1000.00"));
        responseDTO.setSaldoDisponible(new BigDecimal("500.00"));
        responseDTO.setTipoTarjeta(TipoTarjeta.CLASICA);
        responseDTO.setEstado(EstadoTarjeta.ACTIVA);

        when(tarjetaDebitoService.crearTarjeta(any(TarjetaDebitoCreateDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/v1/tarjetas-debito")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numeroTarjeta").value("5428123456789012"))
                .andExpect(jsonPath("$.nombreTitular").value("Juan Pérez"))
                .andExpect(jsonPath("$.cedula").value("1234567890"))
                .andExpect(jsonPath("$.estado").value("ACTIVA"));
    }

    @Test
    public void testObtenerTarjetaPorId_Exitoso() throws Exception {
        // Arrange
        Long tarjetaId = 1L;
        TarjetaDebitoDTO responseDTO = new TarjetaDebitoDTO();
        responseDTO.setId(tarjetaId);
        responseDTO.setNumeroTarjeta("5428123456789012");
        responseDTO.setNombreTitular("Juan Pérez");
        responseDTO.setEstado(EstadoTarjeta.ACTIVA);

        when(tarjetaDebitoService.obtenerTarjetaPorId(tarjetaId)).thenReturn(Optional.of(responseDTO));

        // Act & Assert
        mockMvc.perform(get("/api/v1/tarjetas-debito/{id}", tarjetaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numeroTarjeta").value("5428123456789012"))
                .andExpect(jsonPath("$.nombreTitular").value("Juan Pérez"));
    }

    @Test
    public void testObtenerTarjetaPorId_NoEncontrada() throws Exception {
        // Arrange
        Long tarjetaId = 999L;
        when(tarjetaDebitoService.obtenerTarjetaPorId(tarjetaId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/v1/tarjetas-debito/{id}", tarjetaId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testObtenerTodasLasTarjetas_Exitoso() throws Exception {
        // Arrange
        List<TarjetaDebitoDTO> tarjetas = Arrays.asList(
                createTarjetaDTO(1L, "5428123456789012", "Juan Pérez"),
                createTarjetaDTO(2L, "5428234567890123", "María López")
        );

        when(tarjetaDebitoService.obtenerTodasLasTarjetas()).thenReturn(tarjetas);

        // Act & Assert
        mockMvc.perform(get("/api/v1/tarjetas-debito"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreTitular").value("Juan Pérez"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombreTitular").value("María López"));
    }

    @Test
    public void testBloquearTarjeta_Exitoso() throws Exception {
        // Arrange
        Long tarjetaId = 1L;
        TarjetaDebitoDTO responseDTO = new TarjetaDebitoDTO();
        responseDTO.setId(tarjetaId);
        responseDTO.setEstado(EstadoTarjeta.BLOQUEADA);

        when(tarjetaDebitoService.bloquearTarjeta(tarjetaId)).thenReturn(Optional.of(responseDTO));

        // Act & Assert
        mockMvc.perform(put("/api/v1/tarjetas-debito/{id}/bloquear", tarjetaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("BLOQUEADA"));
    }

    @Test
    public void testEliminarTarjeta_Exitoso() throws Exception {
        // Arrange
        Long tarjetaId = 1L;
        when(tarjetaDebitoService.eliminarTarjeta(tarjetaId)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/tarjetas-debito/{id}", tarjetaId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testEliminarTarjeta_NoEncontrada() throws Exception {
        // Arrange
        Long tarjetaId = 999L;
        when(tarjetaDebitoService.eliminarTarjeta(tarjetaId)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/tarjetas-debito/{id}", tarjetaId))
                .andExpect(status().isNotFound());
    }

    private TarjetaDebitoDTO createTarjetaDTO(Long id, String numeroTarjeta, String nombreTitular) {
        TarjetaDebitoDTO dto = new TarjetaDebitoDTO();
        dto.setId(id);
        dto.setNumeroTarjeta(numeroTarjeta);
        dto.setNombreTitular(nombreTitular);
        dto.setEstado(EstadoTarjeta.ACTIVA);
        dto.setTipoTarjeta(TipoTarjeta.CLASICA);
        dto.setLimiteDiario(new BigDecimal("1000.00"));
        dto.setSaldoDisponible(new BigDecimal("500.00"));
        return dto;
    }
}
