package com.scaler.bookmyshow.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Payment extends BaseModel{

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private Date timeOfPayment;
    private double amount;
    private String referenceId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    private Ticket ticket;
}
