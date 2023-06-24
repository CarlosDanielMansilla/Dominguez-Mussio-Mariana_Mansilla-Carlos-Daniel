package com.backend.IntegradorFinal.controller;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import com.backend.IntegradorFinal.service.imp.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping()
    public List<OdontologoDto> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

    //-- este no es necesario, no??
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarOdontologoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), null, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), null, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologo), null, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }

}

