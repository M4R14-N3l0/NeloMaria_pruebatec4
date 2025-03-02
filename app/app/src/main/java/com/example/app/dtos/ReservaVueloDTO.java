package com.example.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaVueloDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("fecha_reserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaReserva;

    @JsonProperty("persona_id")
    private Long personaId;

    @JsonProperty("vuelo_id")
    private Long vueloId;
}
