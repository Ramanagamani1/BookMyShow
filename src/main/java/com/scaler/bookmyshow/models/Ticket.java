package com.scaler.bookmyshow.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Ticket extends BaseModel{

    @ManyToOne
    private User bookedBy;

    @ManyToOne
    private Show show;


    @ManyToMany
    private List<ShowSeat> showSeats;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    private Date timeOfBooking;

}
