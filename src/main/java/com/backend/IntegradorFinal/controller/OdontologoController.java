package com.backend.IntegradorFinal.controller;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);

    @Autowired
    private IOdontologoService odontologoService;

    @GetMapping()
    public ResponseEntity<List<OdontologoDto>> listarTodos(){
        ResponseEntity<List<OdontologoDto>> respuesta;
        List<OdontologoDto> odontologoDtos= odontologoService.listarOdontologos();
        if(!odontologoDtos.isEmpty()) respuesta = new ResponseEntity<>(odontologoDtos, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return respuesta;

    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarPorId(@PathVariable Long id){
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);
        if(odontologoDto != null) respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        if(odontologoDto != null) respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(odontologo);
        if(odontologoDto != null) respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return respuesta;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id)  {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo Eliminado");
    }
}


