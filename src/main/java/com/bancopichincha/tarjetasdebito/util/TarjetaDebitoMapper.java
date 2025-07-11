package com.bancopichincha.tarjetasdebito.util;

import com.bancopichincha.tarjetasdebito.model.dto.TarjetaDebitoDTO;
import com.bancopichincha.tarjetasdebito.model.entity.TarjetaDebito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarjetaDebitoMapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Convertir Entity a DTO
     */
    public TarjetaDebitoDTO toDTO(TarjetaDebito tarjeta) {
        return modelMapper.map(tarjeta, TarjetaDebitoDTO.class);
    }

    /**
     * Convertir DTO a Entity
     */
    public TarjetaDebito toEntity(TarjetaDebitoDTO tarjetaDTO) {
        return modelMapper.map(tarjetaDTO, TarjetaDebito.class);
    }
}
