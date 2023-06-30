package com.backend.IntegradorFinal.service.imp;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.dto.PacienteDto;
import com.backend.IntegradorFinal.entity.Domicilio;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.entity.Paciente;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaInsertarUnPaciente(){
        Paciente pacienteInsertar = new Paciente("Lu","Murga","654654", LocalDate.of(2023,06,30), new Domicilio("calle",6,"localildad","provincia"));
        PacienteDto pacienteDto = pacienteService.guardarPaciente(pacienteInsertar);

        Assertions.assertNotNull(pacienteDto);
        Assertions.assertNotNull(pacienteDto.getId());
    }

    @Test
    @Order(2)
    void deberiaListarUnSoloPaciente(){
        List<PacienteDto> pacienteDtos = pacienteService.listarPacientes();
        assertEquals(1,pacienteDtos.size());
    }

    @Test
    @Order(3)
    void deberiaEliminarPacienteId1() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }

}