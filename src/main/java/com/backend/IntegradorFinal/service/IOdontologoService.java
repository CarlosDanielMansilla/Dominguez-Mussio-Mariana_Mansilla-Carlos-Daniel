package com.backend.IntegradorFinal.service;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    List<OdontologoDto> listarOdontologos();

    OdontologoDto buscarOdontologoPorId(Long id);

    OdontologoDto registrarOdontologo(Odontologo odontologo);

    OdontologoDto actualizarOdontologo(Odontologo odontologo);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
