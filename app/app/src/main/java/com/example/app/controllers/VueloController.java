package com.example.app.controllers;
import com.example.app.dtos.VueloDTO;
import com.example.app.services.VueloServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class VueloController {

    private final VueloServicio vueloService;

    public VueloController(VueloServicio vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping ("/flights")
    public List<VueloDTO> getAllVuelos() {
        return vueloService.getAllVuelos();
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<VueloDTO> getVueloById(@PathVariable Long id) {
        return ResponseEntity.ok(vueloService.getVueloById(id));
    }

    @PostMapping ("/flights/new")
    public ResponseEntity<VueloDTO> createVuelo(@RequestBody VueloDTO vueloDTO) {
        return ResponseEntity.ok(vueloService.saveVuelo(vueloDTO));
    }

    @PutMapping("/flights/edit/{id}")
    public ResponseEntity<Optional<VueloDTO>> updateVuelo(@PathVariable Long id, @RequestBody VueloDTO vueloDTO) {
        return ResponseEntity.ok(vueloService.updateVuelo(id, vueloDTO));
    }


    @DeleteMapping("flights/delete/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        vueloService.deleteVuelo(id);
        return ResponseEntity.noContent().build();
    }

}
