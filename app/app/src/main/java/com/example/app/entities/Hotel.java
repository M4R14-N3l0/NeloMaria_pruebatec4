package com.example.app.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_code")
    private Long hotelCode;

    @Column(name = "name_")
    private String name;

    private String place;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_price")
    private Double roomPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "disponibility_date_from")
    private LocalDate availabilityDateFrom;;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "disponibility_date_to")
    private LocalDate availabilityDateTo;

    @Column(name = "is_booked")
    private Boolean isBooked = false;
}
