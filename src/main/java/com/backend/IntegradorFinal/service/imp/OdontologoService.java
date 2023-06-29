package com.backend.IntegradorFinal.service.imp;

import com.backend.IntegradorFinal.dto.OdontologoDto;
import com.backend.IntegradorFinal.entity.Odontologo;
import com.backend.IntegradorFinal.exceptions.ResourceNotFoundException;
import com.backend.IntegradorFinal.repository.OdontologoRepository;
import com.backend.IntegradorFinal.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final ObjectMapper objectMapper;
    private final OdontologoRepository odontologoRepository;


    @Autowired
    public OdontologoService(ObjectMapper objectMapper, OdontologoRepository odontologoRepository) {
        this.objectMapper = objectMapper;
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public List<OdontologoDto> listarOdontologos() {
        List<OdontologoDto> odontologos = odontologoRepository.findAll().stream()
                .map(o -> objectMapper.convertValue(o, OdontologoDto.class)).toList();
        LOGGER.info("Listado de todos los odontologos: {}", odontologos);
        return odontologos;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) {

        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;
        if (odontologoBuscado != null) {
            odontologoDto = objectMapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("Odontologo encontrado: {}", odontologoDto);
        } else {
            LOGGER.info("El id no se encuentra registrado en la base de datos");


        }
        return odontologoDto;
    }


    @Override
    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
        Odontologo odGuardado = odontologoRepository.save(odontologo);
        LOGGER.info("Odontologo guardado: {}",odGuardado);
        return objectMapper.convertValue(odGuardado, OdontologoDto.class);
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoDtoActualizado = null;
        if (odontologoAActualizar != null) {
            odontologoAActualizar = odontologo;
            odontologoRepository.save(odontologoAActualizar);
            odontologoDtoActualizado = objectMapper.convertValue(odontologoAActualizar, OdontologoDto.class);
            LOGGER.warn("Odontologo actualizado: {}",odontologoDtoActualizado);
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");

        }
        return odontologoDtoActualizado;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }
}