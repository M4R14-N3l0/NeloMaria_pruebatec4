package com.example.app.controllers;

import com.example.app.dtos.ReservaVueloDTO;
import com.example.app.services.ReservaVueloServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservas-vuelo")
public class ReservaVueloController {

    private final ReservaVueloServicio reservaVueloService;

    public ReservaVueloController(ReservaVueloServicio reservaVueloService) {
        this.reservaVueloService = reservaVueloService;
    }

    @GetMapping
    public List<ReservaVueloDTO> getAllReservas() {
        return reservaVueloService.obtenerTodasLasReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaVueloDTO> getReservaById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaVueloService.obtenerReservaPorId(id));
    }

    @PostMapping ("/new")
    public ResponseEntity<ReservaVueloDTO> createReserva(@RequestBody ReservaVueloDTO reservaDTO) {
        return ResponseEntity.ok(reservaVueloService.crearReservaVuelo(reservaDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaVueloService.eliminarReservaVuelo(id);
        return ResponseEntity.noContent().build();
    }
}
