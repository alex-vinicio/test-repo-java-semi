package com.bancopichincha.tarjetasdebito.model.entity;

public enum TipoTarjeta {
    CLASICA("Clásica"),
    GOLD("Gold"),
    PLATINUM("Platinum"),
    SIGNATURE("Signature"),
    EMPRESARIAL("Empresarial");

    private final String descripcion;

    TipoTarjeta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
