package com.scaler.bookmyshow.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    private String number;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
