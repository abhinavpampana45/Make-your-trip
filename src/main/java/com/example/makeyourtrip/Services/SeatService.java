package com.example.makeyourtrip.Services;

import com.example.makeyourtrip.Enums.SeatType;
import com.example.makeyourtrip.Models.Seat;
import com.example.makeyourtrip.Models.Transport;
import com.example.makeyourtrip.Repositories.SeatRepository;
import com.example.makeyourtrip.Repositories.TransportRepository;
import com.example.makeyourtrip.RequestDto.AddFlightSeatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private SeatRepository seatRepository;

    public String addFlightSeats(AddFlightSeatDto seatDto)throws Exception{

         Optional<Transport>optionalTransport = transportRepository.findById(seatDto.getTransportId());
         if(optionalTransport.isEmpty())
         {
             throw new Exception("Invalid transport id");
         }
         Transport transport = optionalTransport.get();

        for(int i=1;i<= seatDto.getNoOfEconomySeats();i++){

            Seat seat = Seat.builder().seatNo(String.valueOf("E"+i))
                    .seatType(SeatType.ECONOMY)
                    .price(seatDto.getPriceOfEconomySeat())
                    .transport(transport)
                    .build();


            //Bcz of bidirectional setting in the parent class also
            transport.getSeatList().add(seat);
        }

        for(int i=1;i<=seatDto.getNoOfBusinessSeats();i++){

            Seat seat = Seat.builder().seatNo(String.valueOf("B"+i))
                    .seatType(SeatType.BUSINESS)
                    .price(seatDto.getPriceOfBusinessSeat())
                    .transport(transport)
                    .build();

            //Bcz of bidirectional setting in the parent class also
            transport.getSeatList().add(seat);

        }
        transportRepository.save(transport);
        return "Defined seats have been added";
    }
}
