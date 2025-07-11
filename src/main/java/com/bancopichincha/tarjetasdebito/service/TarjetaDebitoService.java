package com.bancopichincha.tarjetasdebito.service;

import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoCreateDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoUpdateDTO;
import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;

import java.util.List;
import java.util.Optional;

public interface TarjetaDebitoService {

    /**
     * Crear una nueva tarjeta de débito
     */
    TarjetaDebitoDTO crearTarjeta(TarjetaDebitoCreateDTO tarjetaCreateDTO);

    /**
     * Obtener todas las tarjetas de débito
     */
    List<TarjetaDebitoDTO> obtenerTodasLasTarjetas();

    /**
     * Obtener una tarjeta por ID
     */
    Optional<TarjetaDebitoDTO> obtenerTarjetaPorId(Long id);

    /**
     * Obtener tarjeta por número de tarjeta
     */
    Optional<TarjetaDebitoDTO> obtenerTarjetaPorNumero(String numeroTarjeta);

    /**
     * Obtener tarjetas por cédula del titular
     */
    List<TarjetaDebitoDTO> obtenerTarjetasPorCedula(String cedula);

    /**
     * Obtener tarjetas por estado
     */
    List<TarjetaDebitoDTO> obtenerTarjetasPorEstado(EstadoTarjeta estado);

    /**
     * Obtener tarjetas por tipo
     */
    List<TarjetaDebitoDTO> obtenerTarjetasPorTipo(TipoTarjeta tipoTarjeta);

    /**
     * Buscar tarjetas por nombre del titular
     */
    List<TarjetaDebitoDTO> buscarTarjetasPorNombre(String nombreTitular);

    /**
     * Actualizar una tarjeta de débito
     */
    Optional<TarjetaDebitoDTO> actualizarTarjeta(Long id, TarjetaDebitoUpdateDTO tarjetaUpdateDTO);

    /**
     * Bloquear una tarjeta
     */
    Optional<TarjetaDebitoDTO> bloquearTarjeta(Long id);

    /**
     * Desbloquear una tarjeta
     */
    Optional<TarjetaDebitoDTO> desbloquearTarjeta(Long id);

    /**
     * Cancelar una tarjeta
     */
    Optional<TarjetaDebitoDTO> cancelarTarjeta(Long id);

    /**
     * Eliminar una tarjeta
     */
    boolean eliminarTarjeta(Long id);

    /**
     * Verificar si existe una tarjeta con el número especificado
     */
    boolean existeTarjetaConNumero(String numeroTarjeta);

    /**
     * Verificar si existe una tarjeta con la cédula especificada
     */
    boolean existeTarjetaConCedula(String cedula);

    /**
     * Obtener tarjetas activas por cédula
     */
    List<TarjetaDebitoDTO> obtenerTarjetasActivasPorCedula(String cedula);

    /**
     * Obtener tarjetas próximas a vencer
     */
    List<TarjetaDebitoDTO> obtenerTarjetasProximasAVencer();

    /**
     * Actualizar tarjetas vencidas
     */
    void actualizarTarjetasVencidas();

    /**
     * Obtener estadísticas de tarjetas por estado
     */
    Long contarTarjetasPorEstado(EstadoTarjeta estado);
}
