package com.example.makeyourtrip.Repositories;

import com.example.makeyourtrip.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    //@Query(value = "select * from bookings where journeyDate =:journeyDate and transportId =:transportId",nativeQuery = true)
    List<Booking> findBookingsByJourneyDateAndTransportId(LocalDate journeyDate, Integer transportId);
}
