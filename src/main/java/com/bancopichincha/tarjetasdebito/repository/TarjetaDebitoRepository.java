package com.bancopichincha.tarjetasdebito.repository;

import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TarjetaDebito;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarjetaDebitoRepository extends JpaRepository<TarjetaDebito, Long> {

    /**
     * Buscar tarjeta por número de tarjeta
     */
    Optional<TarjetaDebito> findByNumeroTarjeta(String numeroTarjeta);

    /**
     * Buscar tarjetas por cédula del titular
     */
    List<TarjetaDebito> findByCedula(String cedula);

    /**
     * Buscar tarjetas por estado
     */
    List<TarjetaDebito> findByEstado(EstadoTarjeta estado);

    /**
     * Buscar tarjetas por tipo
     */
    List<TarjetaDebito> findByTipoTarjeta(TipoTarjeta tipoTarjeta);

    /**
     * Buscar tarjetas por nombre del titular (búsqueda parcial)
     */
    List<TarjetaDebito> findByNombreTitularContainingIgnoreCase(String nombreTitular);

    /**
     * Verificar si existe una tarjeta con el número especificado
     */
    boolean existsByNumeroTarjeta(String numeroTarjeta);

    /**
     * Verificar si existe una tarjeta con la cédula especificada
     */
    boolean existsByCedula(String cedula);

    /**
     * Obtener tarjetas activas por cédula
     */
    @Query("SELECT t FROM TarjetaDebito t WHERE t.cedula = :cedula AND t.estado = :estado")
    List<TarjetaDebito> findActiveTarjetasByCedula(@Param("cedula") String cedula, @Param("estado") EstadoTarjeta estado);

    /**
     * Obtener tarjetas por estado y tipo
     */
    @Query("SELECT t FROM TarjetaDebito t WHERE t.estado = :estado AND t.tipoTarjeta = :tipoTarjeta")
    List<TarjetaDebito> findByEstadoAndTipoTarjeta(@Param("estado") EstadoTarjeta estado, @Param("tipoTarjeta") TipoTarjeta tipoTarjeta);

    /**
     * Contar tarjetas por estado
     */
    @Query("SELECT COUNT(t) FROM TarjetaDebito t WHERE t.estado = :estado")
    Long countByEstado(@Param("estado") EstadoTarjeta estado);

    /**
     * Obtener tarjetas próximas a vencer (en los próximos 30 días)
     */
    @Query("SELECT t FROM TarjetaDebito t WHERE t.fechaExpiracion <= CURRENT_DATE + 30 AND t.estado = 'ACTIVA'")
    List<TarjetaDebito> findTarjetasProximasAVencer();

    /**
     * Obtener tarjetas vencidas
     */
    @Query("SELECT t FROM TarjetaDebito t WHERE t.fechaExpiracion < CURRENT_DATE AND t.estado != 'VENCIDA'")
    List<TarjetaDebito> findTarjetasVencidas();
}
