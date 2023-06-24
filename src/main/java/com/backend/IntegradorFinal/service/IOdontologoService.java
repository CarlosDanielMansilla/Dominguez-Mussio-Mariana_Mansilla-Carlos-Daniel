package com.backend.IntegradorFinal.service;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id);

    List<OdontologoDto> listarOdontologos();

    OdontologoDto registrarOdontologo(Odontologo odontologo);

    OdontologoDto actualizarOdontologo(Odontologo odontologo);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
