package com.bancopichincha.tarjetasdebito.config;

import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TarjetaDebito;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import com.bancopichincha.tarjetasdebito.repository.TarjetaDebitoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private TarjetaDebitoRepository tarjetaDebitoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (tarjetaDebitoRepository.count() == 0) {
            logger.info("Inicializando datos de prueba...");
            initializeSampleData();
            logger.info("Datos de prueba inicializados exitosamente");
        } else {
            logger.info("Los datos ya están inicializados");
        }
    }

    private void initializeSampleData() {
        List<TarjetaDebito> tarjetasMock = Arrays.asList(
                // Tarjeta 1 - Juan Pérez
                new TarjetaDebito(
                        "5428123456789012",
                        "Juan Carlos Pérez González",
                        "1234567890",
                        LocalDate.now().plusYears(5),
                        "123",
                        new BigDecimal("1000.00"),
                        new BigDecimal("2500.50"),
                        TipoTarjeta.CLASICA
                ),

                // Tarjeta 2 - María López
                new TarjetaDebito(
                        "5428234567890123",
                        "María Fernanda López Martínez",
                        "2345678901",
                        LocalDate.now().plusYears(4),
                        "456",
                        new BigDecimal("2000.00"),
                        new BigDecimal("5000.00"),
                        TipoTarjeta.GOLD
                ),

                // Tarjeta 3 - Carlos Rodríguez
                new TarjetaDebito(
                        "5428345678901234",
                        "Carlos Alberto Rodríguez Vega",
                        "3456789012",
                        LocalDate.now().plusYears(3),
                        "789",
                        new BigDecimal("5000.00"),
                        new BigDecimal("12000.75"),
                        TipoTarjeta.PLATINUM
                ),

                // Tarjeta 4 - Ana Torres (Bloqueada)
                new TarjetaDebito(
                        "5428456789012345",
                        "Ana Sofía Torres Jiménez",
                        "4567890123",
                        LocalDate.now().plusYears(2),
                        "321",
                        new BigDecimal("1500.00"),
                        new BigDecimal("800.25"),
                        TipoTarjeta.CLASICA
                ),

                // Tarjeta 5 - Roberto Silva
                new TarjetaDebito(
                        "5428567890123456",
                        "Roberto Miguel Silva Castillo",
                        "5678901234",
                        LocalDate.now().plusYears(1),
                        "654",
                        new BigDecimal("3000.00"),
                        new BigDecimal("7500.00"),
                        TipoTarjeta.SIGNATURE
                ),

                // Tarjeta 6 - Patricia Morales
                new TarjetaDebito(
                        "5428678901234567",
                        "Patricia Elena Morales Herrera",
                        "6789012345",
                        LocalDate.now().plusYears(5),
                        "987",
                        new BigDecimal("2500.00"),
                        new BigDecimal("4200.30"),
                        TipoTarjeta.GOLD
                ),

                // Tarjeta 7 - Diego Vargas (Empresarial)
                new TarjetaDebito(
                        "5428789012345678",
                        "Diego Fernando Vargas Sánchez",
                        "7890123456",
                        LocalDate.now().plusYears(3),
                        "147",
                        new BigDecimal("10000.00"),
                        new BigDecimal("25000.00"),
                        TipoTarjeta.EMPRESARIAL
                ),

                // Tarjeta 8 - Carmen Delgado (Próxima a vencer)
                new TarjetaDebito(
                        "5428890123456789",
                        "Carmen Lucía Delgado Ruiz",
                        "8901234567",
                        LocalDate.now().plusDays(15), // Próxima a vencer
                        "258",
                        new BigDecimal("1200.00"),
                        new BigDecimal("350.75"),
                        TipoTarjeta.CLASICA
                ),

                // Tarjeta 9 - Andrés Flores
                new TarjetaDebito(
                        "5428901234567890",
                        "Andrés Felipe Flores Mendoza",
                        "9012345678",
                        LocalDate.now().plusYears(4),
                        "369",
                        new BigDecimal("4000.00"),
                        new BigDecimal("8900.50"),
                        TipoTarjeta.PLATINUM
                ),

                // Tarjeta 10 - Lucía Ramírez (Suspendida)
                new TarjetaDebito(
                        "5428012345678901",
                        "Lucía Alejandra Ramírez Castro",
                        "0123456789",
                        LocalDate.now().plusYears(2),
                        "741",
                        new BigDecimal("1800.00"),
                        new BigDecimal("1200.00"),
                        TipoTarjeta.GOLD
                )
        );

        // Configurar estados específicos para algunas tarjetas
        tarjetasMock.get(3).setEstado(EstadoTarjeta.BLOQUEADA); // Ana Torres
        tarjetasMock.get(9).setEstado(EstadoTarjeta.SUSPENDIDA); // Lucía Ramírez

        // Configurar información de contacto adicional
        tarjetasMock.get(0).setTelefono("0987654321");
        tarjetasMock.get(0).setEmail("juan.perez@email.com");
        
        tarjetasMock.get(1).setTelefono("0987654322");
        tarjetasMock.get(1).setEmail("maria.lopez@email.com");
        
        tarjetasMock.get(2).setTelefono("0987654323");
        tarjetasMock.get(2).setEmail("carlos.rodriguez@email.com");
        
        tarjetasMock.get(4).setTelefono("0987654325");
        tarjetasMock.get(4).setEmail("roberto.silva@email.com");
        
        tarjetasMock.get(6).setTelefono("0987654327");
        tarjetasMock.get(6).setEmail("diego.vargas@empresa.com");

        // Guardar todas las tarjetas
        tarjetaDebitoRepository.saveAll(tarjetasMock);
        
        logger.info("Se han creado {} tarjetas de prueba", tarjetasMock.size());
        
        // Mostrar resumen de datos creados
        logger.info("Resumen de datos creados:");
        logger.info("- Tarjetas ACTIVAS: {}", tarjetaDebitoRepository.countByEstado(EstadoTarjeta.ACTIVA));
        logger.info("- Tarjetas BLOQUEADAS: {}", tarjetaDebitoRepository.countByEstado(EstadoTarjeta.BLOQUEADA));
        logger.info("- Tarjetas SUSPENDIDAS: {}", tarjetaDebitoRepository.countByEstado(EstadoTarjeta.SUSPENDIDA));
        logger.info("- Tarjetas CLÁSICAS: {}", tarjetaDebitoRepository.findByTipoTarjeta(TipoTarjeta.CLASICA).size());
        logger.info("- Tarjetas GOLD: {}", tarjetaDebitoRepository.findByTipoTarjeta(TipoTarjeta.GOLD).size());
        logger.info("- Tarjetas PLATINUM: {}", tarjetaDebitoRepository.findByTipoTarjeta(TipoTarjeta.PLATINUM).size());
        logger.info("- Tarjetas SIGNATURE: {}", tarjetaDebitoRepository.findByTipoTarjeta(TipoTarjeta.SIGNATURE).size());
        logger.info("- Tarjetas EMPRESARIAL: {}", tarjetaDebitoRepository.findByTipoTarjeta(TipoTarjeta.EMPRESARIAL).size());
    }
}
