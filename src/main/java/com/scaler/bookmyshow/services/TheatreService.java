package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.exceptions.CityNotFoundException;
import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.AuditoriumRepository;
import com.scaler.bookmyshow.repositories.CityRepository;
import com.scaler.bookmyshow.repositories.SeatRepository;
import com.scaler.bookmyshow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.chrono.ThaiBuddhistChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TheatreService {
     private CityRepository cityRepository;
     private TheatreRepository theatreRepository;
     private AuditoriumRepository auditoriumRepository;
     private SeatRepository seatRepository;

     @Autowired
     public TheatreService(TheatreRepository theatreRepository, CityRepository cityRepository,
                           AuditoriumRepository auditoriumRepository,
                           SeatRepository seatRepository) {
         this.theatreRepository = theatreRepository;
         this.cityRepository = cityRepository;
         this.auditoriumRepository = auditoriumRepository;
         this.seatRepository = seatRepository;
     }

     public Theatre createTheatre(String name, String address, Long cityId) throws CityNotFoundException {
         Optional<City> cityOptional = this.cityRepository.findById(cityId);
         if (!cityOptional.isPresent()) {
             throw new CityNotFoundException("No city with given id");
         }
         Theatre newTheatre = new Theatre();
         newTheatre.setName(name);
         newTheatre.setAddress(address);
         Theatre savedTheatre = theatreRepository.save(newTheatre);

         City dbCity = cityOptional.get();
         dbCity.getTheatres().add(savedTheatre);
         this.cityRepository.save(dbCity);

         return savedTheatre ;
     }

     public Theatre addAuditorium(Long theatreId, String name, int capacity) {
         Theatre theatre = theatreRepository.findById(theatreId).get();
         Auditorium auditorium = new Auditorium();
         auditorium.setName(name);
         auditorium.setTheatre(theatre);
         auditorium.setCapacity(capacity);

         Auditorium savedAuditorium = auditoriumRepository.save(auditorium);
         theatre.getAuditoriums().add(savedAuditorium);
         return theatreRepository.save(theatre);
     }

    public void addSeats(Long auditoriumId, Map<SeatType, Integer> seatCount) {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId).get();

        List<Seat> seats = new ArrayList();

        for(Map.Entry<SeatType,Integer> entry: seatCount.entrySet()) {

            for(int i=0;i<entry.getValue();i++) {
                Seat seat = new Seat();
                seat.setSeatType(entry.getKey());
                seat.setNumber(entry.getKey().toString() + Integer.toString(i+1));
                seats.add(seat);
            }
        }

        List<Seat> savedSeats = new ArrayList<>();

        for(Seat seat: seats) {
            savedSeats.add(seatRepository.save(seat));
        }

        auditorium.setSeats(savedSeats);
        auditoriumRepository.save(auditorium);
    }
}
