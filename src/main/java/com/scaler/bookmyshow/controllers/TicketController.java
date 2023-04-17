package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.exceptions.ShowSeatNotAvailableException;
import com.scaler.bookmyshow.models.Ticket;
import com.scaler.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    private TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Ticket bookTicket(Long show_id, List<Long> showSeatIds,Long userId) throws ShowSeatNotAvailableException {

        return this.ticketService.bookTicket(show_id, showSeatIds, userId);
    }
}
