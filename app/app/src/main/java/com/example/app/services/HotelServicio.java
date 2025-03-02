package com.example.app.services;

import com.example.app.dtos.HotelDTO;
import com.example.app.entities.Hotel;
import com.example.app.repositories.HotelRepositorioInterfaz;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServicio {

    private final HotelRepositorioInterfaz hotelRepository;

    public HotelServicio(HotelRepositorioInterfaz hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // Convertir Entidad a DTO
    private HotelDTO convertToDTO(Hotel hotel) {
        return new HotelDTO(
                hotel.getHotelCode(),
                hotel.getName(),
                hotel.getPlace(),
                hotel.getRoomType(),
                hotel.getRoomPrice(),
                hotel.getAvailabilityDateFrom(),
                hotel.getAvailabilityDateTo(),
                hotel.getIsBooked(),
                null
        );
    }

    // Guardar un nuevo hotel
    public HotelDTO saveHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel(
                null,
                hotelDTO.getName_(),
                hotelDTO.getPlace(),
                hotelDTO.getRoomType(),
                hotelDTO.getRoomPrice(),
                hotelDTO.getDisponibilityDateFrom(),
                hotelDTO.getDisponibilityDateTo(),
                hotelDTO.getIsBooked()

        );
        Hotel savedHotel = hotelRepository.save(hotel);
        return convertToDTO(savedHotel);
    }


    public Optional<HotelDTO> updateHotel(Long id, HotelDTO hotelDTO) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setName(hotelDTO.getName_());
            hotel.setPlace(hotelDTO.getPlace());
            hotel.setRoomType(hotelDTO.getRoomType());
            hotel.setRoomPrice(hotelDTO.getRoomPrice());
            hotel.setAvailabilityDateFrom(hotelDTO.getDisponibilityDateFrom());
            hotel.setAvailabilityDateTo(hotelDTO.getDisponibilityDateTo());
            hotel.setIsBooked(hotelDTO.getIsBooked());

            Hotel updatedHotel = hotelRepository.save(hotel);
            return convertToDTO(updatedHotel);
        });
    }


    public boolean deleteHotel(Long id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener todos los hoteles
    public List<HotelDTO> getAllHoteles() {
        return hotelRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public Optional<HotelDTO> getHotelById(Long id) {
        return hotelRepository.findById(id).map(this::convertToDTO);
    }


    public List<HotelDTO> findByPlace(String place) {
        return hotelRepository.findByPlace(place)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



}
