package com.example.makeyourtrip.Controllers;

import com.example.makeyourtrip.RequestDto.AddFlightSeatDto;
import com.example.makeyourtrip.Services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/addFlightSeats")
    public ResponseEntity addFlightSeats(@RequestBody AddFlightSeatDto seatDto){
        try
        {
            String res = seatService.addFlightSeats(seatDto);
            return new ResponseEntity(res,HttpStatus.OK);

        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
