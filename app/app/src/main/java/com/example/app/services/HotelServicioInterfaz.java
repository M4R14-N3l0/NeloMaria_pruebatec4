package com.example.app.services;

import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Hotel;

import java.util.List;

public interface HotelServicioInterfaz {

    List<HotelDTO> buscarTodos();

    HotelDTO obtenerHotelPorId(Long id);

    HotelDTO crearHotel(HotelDTO hotelDTO);

    HotelDTO actualizarHotel(Long id, HotelDTO hotelDTO);

    void eliminarHotel(Long id);

    List<HotelDTO> buscarHotelesPorLugar(String place);

    List<HotelDTO> buscarHotelesPorDisponibilidad(String from, String to);

    // Conversores entre entidad y DTO
    HotelDTO convertirHaciaDTO(Hotel hotel);

    Hotel convertirHaciaEntidad(HotelDTO hotelDTO);
}
