package com.example.app.controllers;

import com.example.app.dtos.PersonaDTO;
import com.example.app.dtos.ReservaHotelDTO;
import com.example.app.services.ReservaHotelServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas-hotel")
public class ReservaHotelController {

    private final ReservaHotelServicio reservaHotelService;

    public ReservaHotelController(ReservaHotelServicio reservaHotelService) {
        this.reservaHotelService = reservaHotelService;
    }

    @GetMapping
    public List<ReservaHotelDTO> getAllReservas() {
        return reservaHotelService.getAllReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaHotelDTO> getReservaById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaHotelService.getReservaById(id));
    }

    @PostMapping ("/new")
    public ResponseEntity<ReservaHotelDTO> createReserva(@RequestBody ReservaHotelDTO reservaDTO) {
        return ResponseEntity.ok(reservaHotelService.crearReservaHotel(reservaDTO));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaHotelService.cancelReserva(id);
        return ResponseEntity.noContent().build();
    }
}
