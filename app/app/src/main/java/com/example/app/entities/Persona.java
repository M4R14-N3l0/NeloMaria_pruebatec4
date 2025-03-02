package com.example.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    // Relación OneToMany con ReservaVuelo
    @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReservaVuelo> reservaVuelo;

    // Relación OneToMany con ReservaHotel
    @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore // Evita problemas de serialización
    private List<ReservaHotel> reservaHotel;
}

