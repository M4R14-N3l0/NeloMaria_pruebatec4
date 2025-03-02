package com.example.app.services;

import com.example.app.dtos.ReservaHotelDTO;
import java.util.List;

public interface ReservaHotelInterfaz {

    List<ReservaHotelDTO> obtenerTodasLasReservas();

    ReservaHotelDTO obtenerReservaPorId(Long id);

    ReservaHotelDTO crearReservaHotel(ReservaHotelDTO reservaHotelDTO);

    ReservaHotelDTO actualizarReservaHotel(Long id, ReservaHotelDTO reservaHotelDTO);

    void eliminarReservaHotel(Long id);
}
