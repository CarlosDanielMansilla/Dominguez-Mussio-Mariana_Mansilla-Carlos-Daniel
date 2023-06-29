package com.backend.IntegradorFinal.service;

import com.backend.IntegradorFinal.dto.TurnoDto;
import com.backend.IntegradorFinal.entity.Turno;
import com.backend.IntegradorFinal.exceptions.BadRequestException;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno) throws BadRequestException;

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(Long id) throws ResourceNotFoundException;


}
