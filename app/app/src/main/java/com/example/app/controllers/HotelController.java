package com.example.app.controllers;

import com.example.app.dtos.HotelDTO;
import com.example.app.services.HotelServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class HotelController {

    private final HotelServicio hotelService;

    public HotelController(HotelServicio hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping ("/hotels")
    public List<HotelDTO> getAllHoteles() {
        return hotelService.getAllHoteles();
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Optional<HotelDTO>> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping("/hotels/new")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.saveHotel(hotelDTO));
    }

    @PutMapping("/hotels/edit/{id}")
    public ResponseEntity<Optional<HotelDTO>> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.updateHotel(id, hotelDTO));
    }

    @DeleteMapping("hotels/delete/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
