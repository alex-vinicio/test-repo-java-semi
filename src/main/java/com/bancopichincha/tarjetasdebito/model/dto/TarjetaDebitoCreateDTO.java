package com.bancopichincha.tarjetasdebito.model.dto;

import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TarjetaDebitoCreateDTO {

    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreTitular;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(regexp = "\\d{10}", message = "La cédula debe tener exactamente 10 dígitos")
    private String cedula;

    @NotNull(message = "El límite diario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El límite diario debe ser mayor que 0")
    private BigDecimal limiteDiario;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo inicial debe ser mayor o igual a 0")
    private BigDecimal saldoInicial;

    @NotNull(message = "El tipo de tarjeta es obligatorio")
    private TipoTarjeta tipoTarjeta;

    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener exactamente 10 dígitos")
    private String telefono;

    @Email(message = "El email debe tener un formato válido")
    private String email;

    // Constructores
    public TarjetaDebitoCreateDTO() {}

    public TarjetaDebitoCreateDTO(String nombreTitular, String cedula, BigDecimal limiteDiario,
                                 BigDecimal saldoInicial, TipoTarjeta tipoTarjeta) {
        this.nombreTitular = nombreTitular;
        this.cedula = cedula;
        this.limiteDiario = limiteDiario;
        this.saldoInicial = saldoInicial;
        this.tipoTarjeta = tipoTarjeta;
    }

    // Getters y Setters
    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public BigDecimal getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(BigDecimal limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
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
        return "TarjetaDebitoCreateDTO{" +
                "nombreTitular='" + nombreTitular + '\'' +
                ", cedula='" + cedula + '\'' +
                ", limiteDiario=" + limiteDiario +
                ", saldoInicial=" + saldoInicial +
                ", tipoTarjeta=" + tipoTarjeta +
                '}';
    }
}
