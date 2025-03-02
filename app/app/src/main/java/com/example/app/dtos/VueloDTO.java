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
public class VueloDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("numero_vuelo")
    private String flightNumber;

    @JsonProperty("nombre_aerolinea")
    private String name;

    @JsonProperty("origen")
    private String origin;

    @JsonProperty("destino")
    private String destination;

    @JsonProperty("tipo_asiento")
    private String seatType;

    @JsonProperty("precio_vuelo")
    private Double flightPrice;

    @JsonProperty("fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date_;

    @JsonProperty("reservas_vuelo")
    private List<Long> reservaVueloIds;
}

