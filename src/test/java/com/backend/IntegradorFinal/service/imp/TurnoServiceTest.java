package com.backend.IntegradorFinal.service.imp;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.dto.PacienteDto;
import com.backend.IntegradorFinal.dto.TurnoDto;
import com.backend.IntegradorFinal.entity.Domicilio;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.entity.Paciente;
import com.backend.IntegradorFinal.entity.Turno;
import com.backend.IntegradorFinal.exceptions.BadRequestException;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private static Paciente paciente;
    private static Odontologo odontologo;

    @BeforeAll
    public static void init(){
        paciente = new Paciente ("Lu","Murga","654654", LocalDate.of(2023,06,30), new Domicilio("calle",6,"localildad","provincia"));
        odontologo = new Odontologo("ab654as","patricia","medina");
    }

    @Test
    @Order(1)
    void deberiaInsertarTurno() throws BadRequestException{
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);


        TurnoDto turnoDto = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDateTime.of(LocalDate.of(2024,10,01), LocalTime.of(12,00))));

        Assertions.assertNotNull(turnoDto);
        Assertions.assertNotNull(turnoDto);

    }


}