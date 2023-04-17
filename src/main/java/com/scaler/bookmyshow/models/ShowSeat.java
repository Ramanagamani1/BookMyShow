package com.scaler.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="show_seat_mapping")
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private ShowSeatState state;
}


/*
  Make a booking
  0. Create a city
  1. Create a theatre
  2. create a auditorium in a theatre
  3. create a movie
  4. create a show
 */