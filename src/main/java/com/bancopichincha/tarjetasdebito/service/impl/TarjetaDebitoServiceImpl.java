package com.bancopichincha.tarjetasdebito.service.impl;

import com.bancopichincha.tarjetasdebito.exception.ResourceNotFoundException;
import com.bancopichincha.tarjetasdebito.exception.BusinessException;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoCreateDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoDTO;
import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoUpdateDTO;
import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TarjetaDebito;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import com.bancopichincha.tarjetasdebito.repository.TarjetaDebitoRepository;
import com.bancopichincha.tarjetasdebito.service.TarjetaDebitoService;
import com.bancopichincha.tarjetasdebito.util.TarjetaDebitoMapper;
import com.bancopichincha.tarjetasdebito.util.TarjetaNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TarjetaDebitoServiceImpl implements TarjetaDebitoService {

    private static final Logger logger = LoggerFactory.getLogger(TarjetaDebitoServiceImpl.class);

    @Autowired
    private TarjetaDebitoRepository tarjetaDebitoRepository;

    @Autowired
    private TarjetaDebitoMapper tarjetaDebitoMapper;

    @Autowired
    private TarjetaNumberGenerator tarjetaNumberGenerator;

    @Override
    public TarjetaDebitoDTO crearTarjeta(TarjetaDebitoCreateDTO tarjetaCreateDTO) {
        logger.info("Creando nueva tarjeta de débito para cédula: {}", tarjetaCreateDTO.getCedula());

        // Validar que no exista una tarjeta activa con la misma cédula
        if (tarjetaDebitoRepository.existsByCedula(tarjetaCreateDTO.getCedula())) {
            List<TarjetaDebito> tarjetasExistentes = tarjetaDebitoRepository.findActiveTarjetasByCedula(
                    tarjetaCreateDTO.getCedula(), EstadoTarjeta.ACTIVA);
            if (!tarjetasExistentes.isEmpty()) {
                throw new BusinessException("Ya existe una tarjeta activa para la cédula: " + tarjetaCreateDTO.getCedula());
            }
        }

        // Crear la entidad tarjeta
        TarjetaDebito tarjeta = new TarjetaDebito();
        tarjeta.setNumeroTarjeta(tarjetaNumberGenerator.generateTarjetaNumber());
        tarjeta.setCvv(tarjetaNumberGenerator.generateCVV());
        tarjeta.setFechaExpiracion(LocalDate.now().plusYears(5)); // Válida por 5 años
        tarjeta.setNombreTitular(tarjetaCreateDTO.getNombreTitular());
        tarjeta.setCedula(tarjetaCreateDTO.getCedula());
        tarjeta.setLimiteDiario(tarjetaCreateDTO.getLimiteDiario());
        tarjeta.setSaldoDisponible(tarjetaCreateDTO.getSaldoInicial());
        tarjeta.setTipoTarjeta(tarjetaCreateDTO.getTipoTarjeta());
        tarjeta.setEstado(EstadoTarjeta.ACTIVA);
        tarjeta.setTelefono(tarjetaCreateDTO.getTelefono());
        tarjeta.setEmail(tarjetaCreateDTO.getEmail());

        // Guardar la tarjeta
        TarjetaDebito tarjetaGuardada = tarjetaDebitoRepository.save(tarjeta);
        logger.info("Tarjeta creada exitosamente con ID: {}", tarjetaGuardada.getId());

        return tarjetaDebitoMapper.toDTO(tarjetaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDebitoDTO> obtenerTodasLasTarjetas() {
        logger.info("Obteniendo todas las tarjetas de débito");
        List<TarjetaDebito> tarjetas = tarjetaDebitoRepository.findAll();
        return tarjetas.stream()
                .map(tarjetaDebitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TarjetaDebitoDTO> obtenerTarjetaPorId(Long id) {
        logger.info("Obteniendo tarjeta con ID: {}", id);
        return tarjetaDebitoRepository.findById(id)
                .map(tarjetaDebitoMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TarjetaDebitoDTO> obtenerTarjetaPorNumero(String numeroTarjeta) {
        logger.info("Obteniendo tarjeta con número: {}", numeroTarjeta);
        return tarjetaDebitoRepository.findByNumeroTarjeta(numeroTarjeta)
                .map(tarjetaDebitoMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDebitoDTO> obtenerTarjetasPorCedula(String cedula) {
        logger.info("Obteniendo tarjetas para cédula: {}", cedula);
        List<TarjetaDebito> tarjetas = tarjetaDebitoRepository.findByCedula(cedula);
        return tarjetas.stream()
                .map(tarjetaDebitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDebitoDTO> obtenerTarjetasPorEstado(EstadoTarjeta estado) {
        logger.info("Obteniendo tarjetas con estado: {}", estado);
        List<TarjetaDebito> tarjetas = tarjetaDebitoRepository.findByEstado(estado);
        return tarjetas.stream()
                .map(tarjetaDebitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDebitoDTO> obtenerTarjetasPorTipo(TipoTarjeta tipoTarjeta) {
        logger.info("Obteniendo tarjetas de tipo: {}", tipoTarjeta);
        List<TarjetaDebito> tarjetas = tarjetaDebitoRepository.findByTipoTarjeta(tipoTarjeta);
        return tarjetas.stream()
                .map(tarjetaDebitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDebitoDTO> buscarTarjetasPorNombre(String nombreTitular) {
        logger.info("Buscando tarjetas por nombre: {}", nombreTitular);
        List<TarjetaDebito> tarjetas = tarjetaDebitoRepository.findByNombreTitularContainingIgnoreCase(nombreTitular);
        return tarjetas.stream()
                .map(tarjetaDebitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TarjetaDebitoDTO> actualizarTarjeta(Long id, TarjetaDebitoUpdateDTO tarjetaUpdateDTO) {
        logger.info("Actualizando tarjeta con ID: {}", id);
        
        Optional<TarjetaDebito> tarjetaOpt = tarjetaDebitoRepository.findById(id);
        if (tarjetaOpt.isEmpty()) {
            logger.warn("Tarjeta con ID {} no encontrada", id);
            return Optional.empty();
        }

        TarjetaDebito tarjeta = tarjetaOpt.get();
        
        // Actualizar solo los campos que no son null
        if (tarjetaUpdateDTO.getLimiteDiario() != null) {
            tarjeta.setLimiteDiario(tarjetaUpdateDTO.getLimiteDiario());
        }
        
        if (tarjetaUpdateDTO.getEstado() != null) {
            tarjeta.setEstado(tarjetaUpdateDTO.getEstado());
        }
        
        if (tarjetaUpdateDTO.getTelefono() != null) {
            tarjeta.setTelefono(tarjetaUpdateDTO.getTelefono());
        }
        
        if (tarjetaUpdateDTO.getEmail() != null) {
            tarjeta.setEmail(tarjetaUpdateDTO.getEmail());
        }

        tarjeta.setFechaActualizacion(LocalDateTime.now());
        
        TarjetaDebito tarjetaActualizada = tarjetaDebitoRepository.save(tarjeta);
        logger.info("Tarjeta actualizada exitosamente con ID: {}", tarjetaActualizada.getId());
        
        return Optional.of(tarjetaDebitoMapper.toDTO(tarjetaActualizada));
    }

    @Override
    public Optional<TarjetaDebitoDTO> bloquearTarjeta(Long id) {
        logger.info("Bloqueando tarjeta con ID: {}", id);
        return cambiarEstadoTarjeta(id, EstadoTarjeta.BLOQUEADA);
    }

    @Override
    public Optional<TarjetaDebitoDTO> desbloquearTarjeta(Long id) {
        logger.info("Desbloqueando tarjeta con ID: {}", id);
        return cambiarEstadoTarjeta(id, EstadoTarjeta.ACTIVA);
    }

    @Override
    public Optional<TarjetaDebitoDTO> cancelarTarjeta(Long id) {
        logger.info("Cancelando tarjeta con ID: {}", id);
        return cambiarEstadoTarjeta(id, EstadoTarjeta.CANCELADA);
    }

    @Override
    public boolean eliminarTarjeta(Long id) {
        logger.info("Eliminando tarjeta con ID: {}", id);
        
        if (!tarjetaDebitoRepository.existsById(id)) {
            logger.warn("Tarjeta con ID {} no encontrada para eliminación", id);
            return false;
        }
        
        tarjetaDebitoRepository.deleteById(id);
        logger.info("Tarjeta con ID {} eliminada exitosamente", id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeTarjetaConNumero(String numeroTarjeta) {
        return tarjetaDebitoRepository.existsByNumeroTarjeta(numeroTarjeta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeTarjetaConCedula(String cedula) {
        return tarjetaDebitoRepository.existsByCedula(cedula);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDebitoDTO> obtenerTarjetasActivasPorCedula(String cedula) {
        logger.info("Obteniendo tarjetas activas para cédula: {}", cedula);
        List<TarjetaDebito> tarjetas = tarjetaDebitoRepository.findActiveTarjetasByCedula(cedula, EstadoTarjeta.ACTIVA);
        return tarjetas.stream()
                .map(tarjetaDebitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarjetaDebitoDTO> obtenerTarjetasProximasAVencer() {
        logger.info("Obteniendo tarjetas próximas a vencer");
        List<TarjetaDebito> tarjetas = tarjetaDebitoRepository.findTarjetasProximasAVencer();
        return tarjetas.stream()
                .map(tarjetaDebitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarTarjetasVencidas() {
        logger.info("Actualizando tarjetas vencidas");
        List<TarjetaDebito> tarjetasVencidas = tarjetaDebitoRepository.findTarjetasVencidas();
        
        tarjetasVencidas.forEach(tarjeta -> {
            tarjeta.setEstado(EstadoTarjeta.VENCIDA);
            tarjeta.setFechaActualizacion(LocalDateTime.now());
        });
        
        tarjetaDebitoRepository.saveAll(tarjetasVencidas);
        logger.info("Se actualizaron {} tarjetas vencidas", tarjetasVencidas.size());
    }

    @Override
    @Transactional(readOnly = true)
    public Long contarTarjetasPorEstado(EstadoTarjeta estado) {
        return tarjetaDebitoRepository.countByEstado(estado);
    }

    private Optional<TarjetaDebitoDTO> cambiarEstadoTarjeta(Long id, EstadoTarjeta nuevoEstado) {
        Optional<TarjetaDebito> tarjetaOpt = tarjetaDebitoRepository.findById(id);
        if (tarjetaOpt.isEmpty()) {
            logger.warn("Tarjeta con ID {} no encontrada", id);
            return Optional.empty();
        }

        TarjetaDebito tarjeta = tarjetaOpt.get();
        tarjeta.setEstado(nuevoEstado);
        tarjeta.setFechaActualizacion(LocalDateTime.now());
        
        TarjetaDebito tarjetaActualizada = tarjetaDebitoRepository.save(tarjeta);
        logger.info("Estado de tarjeta con ID {} cambiado a: {}", id, nuevoEstado);
        
        return Optional.of(tarjetaDebitoMapper.toDTO(tarjetaActualizada));
    }
}
