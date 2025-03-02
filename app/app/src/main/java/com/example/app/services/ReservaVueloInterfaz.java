package com.example.app.services;

import com.example.app.dtos.ReservaVueloDTO;
import java.util.List;

public interface ReservaVueloInterfaz {

    List<ReservaVueloDTO> obtenerTodasLasReservas();

    ReservaVueloDTO obtenerReservaPorId(Long id);

    ReservaVueloDTO crearReservaVuelo(ReservaVueloDTO reservaVueloDTO);

    ReservaVueloDTO actualizarReservaVuelo(Long id, ReservaVueloDTO reservaVueloDTO);

    void eliminarReservaVuelo(Long id);
}
