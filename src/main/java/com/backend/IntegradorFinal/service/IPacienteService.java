package com.backend.IntegradorFinal.service;

import com.backend.IntegradorFinal.dto.PacienteDto;
import com.backend.IntegradorFinal.entity.Paciente;
import com.backend.IntegradorFinal.exceptions.BadRequestException;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id);

    PacienteDto guardarPaciente(Paciente paciente);

    PacienteDto actualizarPaciente(Paciente paciente);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

}
