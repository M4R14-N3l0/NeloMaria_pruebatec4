package com.example.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("apellido")
    private String last_name;

    @JsonProperty("reservas_hotel")
    private List<Long> reservaHotelIds;

    @JsonProperty("reservas_vuelo")
    private List<Long> reservaVueloIds;
}
