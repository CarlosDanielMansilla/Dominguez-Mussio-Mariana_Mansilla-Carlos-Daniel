package com.backend.IntegradorFinal.controller;

import com.backend.IntegradorFinal.dto.TurnoDto;
import com.backend.IntegradorFinal.entity.Turno;
import com.backend.IntegradorFinal.exceptions.BadRequestException;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import com.backend.IntegradorFinal.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")

public class TurnoController {

    private final ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), null, HttpStatus.OK);
    }

    @GetMapping()
    public List<TurnoDto> listarTurnos() {
        return turnoService.listarTodos();
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        return new ResponseEntity<>(turnoService.guardarTurno(turno), null, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado");
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarPaciente(@RequestBody Turno turno) {
        return new ResponseEntity<>(turnoService.actualizarTurno(turno), null, HttpStatus.OK);
    }

}

