package com.bancopichincha.tarjetasdebito.model.dto;

import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public class TarjetaDebitoUpdateDTO {

    @DecimalMin(value = "0.0", inclusive = false, message = "El límite diario debe ser mayor que 0")
    private BigDecimal limiteDiario;

    private EstadoTarjeta estado;

    private String telefono;

    private String email;

    // Constructores
    public TarjetaDebitoUpdateDTO() {}

    public TarjetaDebitoUpdateDTO(BigDecimal limiteDiario, EstadoTarjeta estado) {
        this.limiteDiario = limiteDiario;
        this.estado = estado;
    }

    // Getters y Setters
    public BigDecimal getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(BigDecimal limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public EstadoTarjeta getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarjeta estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TarjetaDebitoUpdateDTO{" +
                "limiteDiario=" + limiteDiario +
                ", estado=" + estado +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
