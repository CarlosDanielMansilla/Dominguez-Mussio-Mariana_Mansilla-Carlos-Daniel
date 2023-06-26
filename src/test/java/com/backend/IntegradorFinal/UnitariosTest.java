package com.backend.IntegradorFinal;

import com.backend.IntegradorFinal.entity.Domicilio;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.entity.Paciente;
import com.backend.IntegradorFinal.entity.Turno;
import com.backend.IntegradorFinal.service.IOdontologoService;
import com.backend.IntegradorFinal.service.IPacienteService;
import com.backend.IntegradorFinal.service.ITurnoService;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UnitariosTest {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    TurnoService turnoService;

    @DisplayName("Datos Iniciales")
    @BeforeEach
    void init() {
        Domicilio domicilio = new Domicilio("Gestido", 5,"Mar", "Lagunas");
        Paciente paciente= new Paciente("Rodriguez","Marcos", "marcos@gmail.com",1234567, LocalDate.of(2023,7,1));
        pacienteService.save(paciente);

        Odontologo odontologo = new Odontologo("Juana", "654321", "Tilcara");
        odontologoService.save(odontologo);

        Turno turno = new Turno(paciente,odontologo,LocalDate.of(2023,7,1));
        turnoService.save(turno);
    }

    @Test
    void getPacientePorId(){
        Optional<Paciente> paciente = pacienteService.searchById(1L);
        Assert.assertTrue(paciente.get().getId().longValue()>=1L);
    }

    @Test
    void getOdontologoPorId(){
        Optional<Odontologo> odontologo = odontologoService.searchById(1L);
        Assert.assertTrue(odontologo.get().getId().longValue()>=1L);
    }

    @Test
    void getTurnoPorId(){
        Optional<Turno> turno = turnoService.searchById(1L);
        Assert.assertTrue(turno.get().getId().longValue()>=1L);
    }

    @Test
    void getPacientesList(){
        List<Paciente> pacientes = pacienteService.list();
        Assert.assertFalse(pacientes.isEmpty());
    }

    @Test
    void getOdontologosList(){
        List<Odontologo> odontologos = odontologoService.list();
        Assert.assertFalse(odontologos.isEmpty());
    }

    @Test
    void getTurnosList(){
        List<Turno> turnos = turnoService.list();
        Assert.assertFalse(turnos.isEmpty());
    }

    @Test
    void updatePacienteName(){
        Optional<Paciente> paciente = pacienteService.searchById(1L);
        paciente.get().setNombre("Jesus");
        Paciente paciente1=pacienteService.update(paciente.get());
        Assert.assertEquals(paciente1.getNombre(),"Jesus");
    }

    @Test
    void updateOdontologoName(){
        Optional<Odontologo> odontologo = odontologoService.searchById(1L);
        odontologo.get().setNombre("Jesus");
        Odontologo odontologo1=odontologoService.update(odontologo.get());
        Assert.assertEquals(odontologo1.getNombre(),"Jesus");
    }

    @Test
    void updateTurnoFecha(){
        Optional<Turno> turno = turnoService.searchById(1L);
        turno.get().setFecha(LocalDate.of(2024,4,30));
        Turno turno1=turnoService.update(turno.get());
        Assert.assertEquals(turno1.getFecha(),LocalDate.of(2024,4,30));
    }


}
