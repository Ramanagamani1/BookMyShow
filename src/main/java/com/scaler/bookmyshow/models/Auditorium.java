package com.scaler.bookmyshow.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Setter
@Getter
public class Auditorium extends BaseModel{
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Seat> seats;

    private int capacity;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AuditoriumFeatues> auditoriumFeatues;

    @ManyToOne
    private Theatre theatre;
}
