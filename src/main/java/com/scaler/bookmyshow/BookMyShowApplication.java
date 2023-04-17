package com.scaler.bookmyshow;

import com.scaler.bookmyshow.controllers.*;
import com.scaler.bookmyshow.dtos.CreateUserRequestDto;
import com.scaler.bookmyshow.models.Language;
import com.scaler.bookmyshow.models.Seat;
import com.scaler.bookmyshow.models.SeatType;
import com.scaler.bookmyshow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {


    private UserController userController;
    private CityController cityController;
    private TheatreController theatreController;
    private ShowController showController;
    private TicketController ticketController;

    @Autowired
    public BookMyShowApplication(UserController userController,CityController cityController,
                                 TheatreController theatreController,
                                 ShowController showController,
                                 TicketController ticketController) {
        this.userController = userController;
        this.cityController = cityController;
        this.theatreController=  theatreController;
        this.showController = showController;
        this.ticketController = ticketController;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setEmail("mani14@gmail.com");
        this.userController.createUser(requestDto);
        this.cityController.addCity("Hyderabad");
        this.theatreController.createTheatre("PVR",
                "RamaTalkies, Vizag", 1L);

        this.theatreController.addAuditorium(1L,"Audi 1", 125);

        Map<SeatType,Integer> seatsForAudi = new HashMap<>();
        seatsForAudi.put(SeatType.VIP,20);
        seatsForAudi.put(SeatType.GOLD, 50);
        seatsForAudi.put(SeatType.SILVER,30);

        this.theatreController.addSeats(1L, seatsForAudi);
        this.showController.createShow(
                0L,
                new Date(),
                new Date(),
                1L,
                null,
                Language.ENGLISH
        );

        /*this.ticketController.bookTicket(
                1L,
                List.of(43L,44L,45L),
                1L
        );*/

        TicketBookRunner ticketBookRunner1 = new TicketBookRunner(
                this.ticketController,
                1L,
                List.of(47L,48L,49L),
                1L
        );
        TicketBookRunner ticketBookRunner2 = new TicketBookRunner(
                this.ticketController,
                1L,
                List.of(49L,50L,51L),
                1L
        );
        Thread thread1 = new Thread(ticketBookRunner1);
        Thread thread2 = new Thread(ticketBookRunner2);
        thread1.start();
        thread2.start();
    }
}


/*
  Create a auditorium and add to theatre
  Create a Movie -> same as City
  Create a Show -> theatre_id, audi_id, movie_id, start_time,end_time
 */
