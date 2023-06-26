package com.backend.IntegradorFinal.controller;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import com.backend.IntegradorFinal.service.imp.OdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Odontologo> odontologolist() throws ResourceNotFoundException{
        logger.info("Llama a listar odontologos");
        return odontologoService.list();

    }
    @GetMapping("/view")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<Odontologo> odontologoView() throws EntityNotFoundException{
        logger.info("Llama a listar odontologos");
        return odontologoService.list();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> buscarOdontologoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), null, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OdontologoDto> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), null, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologo), null, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        if (odontologoService.searchById(id).isPresent()){
            odontologoService.eliminarOdontologo(id);
            logger.info("Se eliminó un odontologo con id="+id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }else{
        logger.info("No se eliminó un odontologo con id="+id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
}

}

