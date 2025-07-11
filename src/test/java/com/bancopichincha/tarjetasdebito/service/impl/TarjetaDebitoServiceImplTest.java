package com.bancopichincha.tarjetasdebito.service.impl;

import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoCreateDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoDTO;
import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TarjetaDebito;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import com.bancopichincha.tarjetasdebito.repository.TarjetaDebitoRepository;
import com.bancopichincha.tarjetasdebito.util.TarjetaDebitoMapper;
import com.bancopichincha.tarjetasdebito.util.TarjetaNumberGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarjetaDebitoServiceImplTest {

    @Mock
    private TarjetaDebitoRepository tarjetaDebitoRepository;

    @Mock
    private TarjetaDebitoMapper tarjetaDebitoMapper;

    @Mock
    private TarjetaNumberGenerator tarjetaNumberGenerator;

    @InjectMocks
    private TarjetaDebitoServiceImpl tarjetaDebitoService;

    @Test
    public void testCrearTarjeta_Exitoso() {
        // Arrange
        TarjetaDebitoCreateDTO createDTO = new TarjetaDebitoCreateDTO();
        createDTO.setNombreTitular("Juan Pérez");
        createDTO.setCedula("1234567890");
        createDTO.setLimiteDiario(new BigDecimal("1000.00"));
        createDTO.setSaldoInicial(new BigDecimal("500.00"));
        createDTO.setTipoTarjeta(TipoTarjeta.CLASICA);

        TarjetaDebito tarjetaGuardada = new TarjetaDebito();
        tarjetaGuardada.setId(1L);
        tarjetaGuardada.setNumeroTarjeta("5428123456789012");
        tarjetaGuardada.setNombreTitular("Juan Pérez");
        tarjetaGuardada.setCedula("1234567890");

        TarjetaDebitoDTO expectedDTO = new TarjetaDebitoDTO();
        expectedDTO.setId(1L);
        expectedDTO.setNumeroTarjeta("5428123456789012");
        expectedDTO.setNombreTitular("Juan Pérez");

        when(tarjetaDebitoRepository.existsByCedula(anyString())).thenReturn(false);
        when(tarjetaNumberGenerator.generateTarjetaNumber()).thenReturn("5428123456789012");
        when(tarjetaNumberGenerator.generateCVV()).thenReturn("123");
        when(tarjetaDebitoRepository.save(any(TarjetaDebito.class))).thenReturn(tarjetaGuardada);
        when(tarjetaDebitoMapper.toDTO(any(TarjetaDebito.class))).thenReturn(expectedDTO);

        // Act
        TarjetaDebitoDTO result = tarjetaDebitoService.crearTarjeta(createDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("5428123456789012", result.getNumeroTarjeta());
        assertEquals("Juan Pérez", result.getNombreTitular());
        verify(tarjetaDebitoRepository, times(1)).save(any(TarjetaDebito.class));
    }

    @Test
    public void testObtenerTarjetaPorId_Encontrada() {
        // Arrange
        Long tarjetaId = 1L;
        TarjetaDebito tarjeta = new TarjetaDebito();
        tarjeta.setId(tarjetaId);
        tarjeta.setNumeroTarjeta("5428123456789012");

        TarjetaDebitoDTO expectedDTO = new TarjetaDebitoDTO();
        expectedDTO.setId(tarjetaId);
        expectedDTO.setNumeroTarjeta("5428123456789012");

        when(tarjetaDebitoRepository.findById(tarjetaId)).thenReturn(Optional.of(tarjeta));
        when(tarjetaDebitoMapper.toDTO(tarjeta)).thenReturn(expectedDTO);

        // Act
        Optional<TarjetaDebitoDTO> result = tarjetaDebitoService.obtenerTarjetaPorId(tarjetaId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(tarjetaId, result.get().getId());
        assertEquals("5428123456789012", result.get().getNumeroTarjeta());
    }

    @Test
    public void testObtenerTarjetaPorId_NoEncontrada() {
        // Arrange
        Long tarjetaId = 999L;
        when(tarjetaDebitoRepository.findById(tarjetaId)).thenReturn(Optional.empty());

        // Act
        Optional<TarjetaDebitoDTO> result = tarjetaDebitoService.obtenerTarjetaPorId(tarjetaId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testObtenerTodasLasTarjetas() {
        // Arrange
        List<TarjetaDebito> tarjetas = Arrays.asList(
                new TarjetaDebito(),
                new TarjetaDebito()
        );

        List<TarjetaDebitoDTO> expectedDTOs = Arrays.asList(
                new TarjetaDebitoDTO(),
                new TarjetaDebitoDTO()
        );

        when(tarjetaDebitoRepository.findAll()).thenReturn(tarjetas);
        when(tarjetaDebitoMapper.toDTO(any(TarjetaDebito.class)))
                .thenReturn(expectedDTOs.get(0))
                .thenReturn(expectedDTOs.get(1));

        // Act
        List<TarjetaDebitoDTO> result = tarjetaDebitoService.obtenerTodasLasTarjetas();

        // Assert
        assertEquals(2, result.size());
        verify(tarjetaDebitoRepository, times(1)).findAll();
    }

    @Test
    public void testBloquearTarjeta_Exitoso() {
        // Arrange
        Long tarjetaId = 1L;
        TarjetaDebito tarjeta = new TarjetaDebito();
        tarjeta.setId(tarjetaId);
        tarjeta.setEstado(EstadoTarjeta.ACTIVA);

        TarjetaDebito tarjetaBloqueada = new TarjetaDebito();
        tarjetaBloqueada.setId(tarjetaId);
        tarjetaBloqueada.setEstado(EstadoTarjeta.BLOQUEADA);

        TarjetaDebitoDTO expectedDTO = new TarjetaDebitoDTO();
        expectedDTO.setId(tarjetaId);
        expectedDTO.setEstado(EstadoTarjeta.BLOQUEADA);

        when(tarjetaDebitoRepository.findById(tarjetaId)).thenReturn(Optional.of(tarjeta));
        when(tarjetaDebitoRepository.save(any(TarjetaDebito.class))).thenReturn(tarjetaBloqueada);
        when(tarjetaDebitoMapper.toDTO(any(TarjetaDebito.class))).thenReturn(expectedDTO);

        // Act
        Optional<TarjetaDebitoDTO> result = tarjetaDebitoService.bloquearTarjeta(tarjetaId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(EstadoTarjeta.BLOQUEADA, result.get().getEstado());
        verify(tarjetaDebitoRepository, times(1)).save(any(TarjetaDebito.class));
    }

    @Test
    public void testEliminarTarjeta_Exitoso() {
        // Arrange
        Long tarjetaId = 1L;
        when(tarjetaDebitoRepository.existsById(tarjetaId)).thenReturn(true);

        // Act
        boolean result = tarjetaDebitoService.eliminarTarjeta(tarjetaId);

        // Assert
        assertTrue(result);
        verify(tarjetaDebitoRepository, times(1)).deleteById(tarjetaId);
    }

    @Test
    public void testEliminarTarjeta_NoEncontrada() {
        // Arrange
        Long tarjetaId = 999L;
        when(tarjetaDebitoRepository.existsById(tarjetaId)).thenReturn(false);

        // Act
        boolean result = tarjetaDebitoService.eliminarTarjeta(tarjetaId);

        // Assert
        assertFalse(result);
        verify(tarjetaDebitoRepository, never()).deleteById(tarjetaId);
    }
}
