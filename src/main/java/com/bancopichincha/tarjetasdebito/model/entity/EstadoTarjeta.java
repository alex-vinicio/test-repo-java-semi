package com.bancopichincha.tarjetasdebito.model.entity;

public enum EstadoTarjeta {
    ACTIVA("Activa"),
    BLOQUEADA("Bloqueada"),
    CANCELADA("Cancelada"),
    VENCIDA("Vencida"),
    SUSPENDIDA("Suspendida");

    private final String descripcion;

    EstadoTarjeta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
