package com.example.app.services;

import com.example.app.dtos.VueloDTO;
import com.example.app.entities.Vuelo;

import java.util.List;

public interface VueloServicioInterfaz {

    List<VueloDTO> buscarTodos();

    VueloDTO obtenerVueloPorId(Long id);

    VueloDTO crearVuelo(VueloDTO vueloDTO);

    VueloDTO actualizarVuelo(Long id, VueloDTO vueloDTO);

    void eliminarVuelo(Long id);

    List<VueloDTO> buscarVuelosPorOrigenYDestino(String origin, String destination);

    List<VueloDTO> buscarVuelosPorFecha(String date);

    // Conversores entre entidad y DTO
    VueloDTO convertirHaciaDTO(Vuelo vuelo);

    Vuelo convertirHaciaEntidad(VueloDTO vueloDTO);
}
