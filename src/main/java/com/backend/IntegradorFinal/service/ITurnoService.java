package com.backend.IntegradorFinal.service;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno) throws BadRequestException;

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(Long id) throws ResourceNotFoundException;


}
