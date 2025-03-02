package com.example.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String name_;

    @JsonProperty("ubicacion")
    private String place;

    @JsonProperty("tipo_habitacion")
    private String roomType;

    @JsonProperty("precio_habitacion")
    private Double roomPrice;

    @JsonProperty("disponible_desde")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate disponibilityDateFrom;

    @JsonProperty("disponible_hasta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate disponibilityDateTo;

    @JsonProperty("reservado")
    private Boolean isBooked;

    @JsonProperty("reservas_hotel")
    private List<Long> reservaHotelIds;
}
