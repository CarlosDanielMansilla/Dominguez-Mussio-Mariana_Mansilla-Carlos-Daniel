package com.backend.IntegradorFinal.service.imp;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.dto.PacienteDto;
import com.backend.IntegradorFinal.dto.TurnoDto;
import com.backend.IntegradorFinal.entity.Turno;
import com.backend.IntegradorFinal.exceptions.BadRequestException;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import com.backend.IntegradorFinal.repository.TurnoRepository;
import com.backend.IntegradorFinal.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoDto = null;
        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if(paciente == null || odontologo == null) {
            if(paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente y el odontologo no se encuentra en nuestra base de datos");
            }
            else if (paciente == null){
                LOGGER.error("El paciente no se encuentra en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
                throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
            }

        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoDto);
        }

        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtoList = turnos.stream()
                .map(TurnoDto::fromTurno)
                .toList();

        LOGGER.info("Lista de todos los turnos: {}", turnoDtoList);
        return turnoDtoList;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;
        if (turnoBuscado != null) {
            turnoDto = TurnoDto.fromTurno(turnoBuscado);
            LOGGER.info("Turno encontrado: {}", turnoDto);
        } else {
            LOGGER.info("El id no se encuentra registrado en la base de datos");


        }
        return turnoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoDtoActualizado = null;
        if (turnoAActualizar != null) {
            turnoAActualizar = turno;
            turnoRepository.save(turnoAActualizar);
            turnoDtoActualizado = TurnoDto.fromTurno(turnoAActualizar);
            LOGGER.warn("Turno actualizado: {}", turnoDtoActualizado);
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el turno no se encuentra registrado");

        }

        return turnoDtoActualizado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }
    }


}
