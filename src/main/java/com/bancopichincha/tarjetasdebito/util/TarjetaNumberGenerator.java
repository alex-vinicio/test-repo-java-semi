package com.bancopichincha.tarjetasdebito.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class TarjetaNumberGenerator {

    private final Random random = new SecureRandom();

    /**
     * Genera un número de tarjeta de 16 dígitos
     * Los primeros 4 dígitos identifican al Banco Pichincha
     */
    public String generateTarjetaNumber() {
        StringBuilder sb = new StringBuilder();
        
        // Prefijo del Banco Pichincha (simulado)
        sb.append("5428");
        
        // Generar los siguientes 12 dígitos
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        
        return sb.toString();
    }

    /**
     * Genera un CVV de 3 dígitos
     */
    public String generateCVV() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
