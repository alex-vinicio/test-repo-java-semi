package com.bancopichincha.tarjetasdebito.model.dto;

import com.bancopichincha.tarjetasdebito.model.entity.EstadoTarjeta;
import com.bancopichincha.tarjetasdebito.model.entity.TipoTarjeta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TarjetaDebitoDTO {

    private Long id;

    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Pattern(regexp = "\\d{16}", message = "El número de tarjeta debe tener exactamente 16 dígitos")
    private String numeroTarjeta;

    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreTitular;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(regexp = "\\d{10}", message = "La cédula debe tener exactamente 10 dígitos")
    private String cedula;

    @NotNull(message = "La fecha de expiración es obligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaExpiracion;

    @NotBlank(message = "El CVV es obligatorio")
    @Pattern(regexp = "\\d{3}", message = "El CVV debe tener exactamente 3 dígitos")
    private String cvv;

    @NotNull(message = "El límite diario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El límite diario debe ser mayor que 0")
    private BigDecimal limiteDiario;

    @NotNull(message = "El saldo disponible es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo disponible debe ser mayor o igual a 0")
    private BigDecimal saldoDisponible;

    @NotNull(message = "El estado de la tarjeta es obligatorio")
    private EstadoTarjeta estado;

    @NotNull(message = "El tipo de tarjeta es obligatorio")
    private TipoTarjeta tipoTarjeta;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaActualizacion;

    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener exactamente 10 dígitos")
    private String telefono;

    @Email(message = "El email debe tener un formato válido")
    private String email;

    // Constructores
    public TarjetaDebitoDTO() {}

    public TarjetaDebitoDTO(String numeroTarjeta, String nombreTitular, String cedula, 
                           LocalDate fechaExpiracion, String cvv, BigDecimal limiteDiario,
                           BigDecimal saldoDisponible, EstadoTarjeta estado, TipoTarjeta tipoTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.cedula = cedula;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.limiteDiario = limiteDiario;
        this.saldoDisponible = saldoDisponible;
        this.estado = estado;
        this.tipoTarjeta = tipoTarjeta;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

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

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(BigDecimal limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public EstadoTarjeta getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarjeta estado) {
        this.estado = estado;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
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
        return "TarjetaDebitoDTO{" +
                "id=" + id +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", cedula='" + cedula + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", limiteDiario=" + limiteDiario +
                ", saldoDisponible=" + saldoDisponible +
                ", estado=" + estado +
                ", tipoTarjeta=" + tipoTarjeta +
                '}';
    }
}
