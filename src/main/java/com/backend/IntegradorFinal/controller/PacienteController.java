package com.backend.IntegradorFinal.controller;

import com.backend.IntegradorFinal.dto.PacienteDto;
import com.backend.IntegradorFinal.entity.Paciente;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import com.backend.IntegradorFinal.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> registrarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.actualizarPaciente(paciente);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return respuesta;
    }

    //GET
    @GetMapping
    public List<PacienteDto> listarTodos() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPorId(@PathVariable Long id){
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.buscarPacientePorId(id);
        if(pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }


    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Paciente Eliminado");
    }
}
