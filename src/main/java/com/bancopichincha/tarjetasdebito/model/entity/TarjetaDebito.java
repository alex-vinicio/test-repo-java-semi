package com.bancopichincha.tarjetasdebito.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarjetas_debito")
public class TarjetaDebito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Pattern(regexp = "\\d{16}", message = "El número de tarjeta debe tener exactamente 16 dígitos")
    @Column(name = "numero_tarjeta", unique = true, nullable = false, length = 16)
    private String numeroTarjeta;

    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre_titular", nullable = false, length = 100)
    private String nombreTitular;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(regexp = "\\d{10}", message = "La cédula debe tener exactamente 10 dígitos")
    @Column(name = "cedula", unique = true, nullable = false, length = 10)
    private String cedula;

    @NotNull(message = "La fecha de expiración es obligatoria")
    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDate fechaExpiracion;

    @NotBlank(message = "El CVV es obligatorio")
    @Pattern(regexp = "\\d{3}", message = "El CVV debe tener exactamente 3 dígitos")
    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @NotNull(message = "El límite diario es obligatorio")
    @Column(name = "limite_diario", nullable = false, precision = 10, scale = 2)
    private BigDecimal limiteDiario;

    @NotNull(message = "El saldo disponible es obligatorio")
    @Column(name = "saldo_disponible", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoDisponible;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoTarjeta estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tarjeta", nullable = false)
    private TipoTarjeta tipoTarjeta;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;

    // Constructores
    public TarjetaDebito() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        this.estado = EstadoTarjeta.ACTIVA;
    }

    public TarjetaDebito(String numeroTarjeta, String nombreTitular, String cedula, 
                        LocalDate fechaExpiracion, String cvv, BigDecimal limiteDiario,
                        BigDecimal saldoDisponible, TipoTarjeta tipoTarjeta) {
        this();
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.cedula = cedula;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.limiteDiario = limiteDiario;
        this.saldoDisponible = saldoDisponible;
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

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "TarjetaDebito{" +
                "id=" + id +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", cedula='" + cedula + '\'' +
                ", fechaExpiracion=" + fechaExpiracion +
                ", limiteDiario=" + limiteDiario +
                ", saldoDisponible=" + saldoDisponible +
                ", estado=" + estado +
                ", tipoTarjeta=" + tipoTarjeta +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
