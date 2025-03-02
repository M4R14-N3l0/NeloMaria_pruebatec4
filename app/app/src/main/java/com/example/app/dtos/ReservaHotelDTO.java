package com.example.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaHotelDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("check_in")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkInDate;

    @JsonProperty("check_out")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkOutDate;

    @JsonProperty("persona_id")
    private Long personaId;

    @JsonProperty("hotel_id")
    private Long hotelId;
}
