package com.backend.IntegradorFinal.service.imp;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologo(){
        Odontologo odontologoInsertar = new Odontologo("ab654as","patricia","medina");
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoInsertar);

        Assertions.assertNotNull(odontologoDto);
        Assertions.assertNotNull(odontologoDto.getId());
    }

    @Test
    @Order(2)
    void deberiaListarUnSoloOdontologo(){
        List<OdontologoDto> odontologoDtos = odontologoService.listarOdontologos();
        assertEquals(1,odontologoDtos.size());
    }

    @Test
    @Order(3)
    void deberiaEliminarOdontologoId1() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }
}