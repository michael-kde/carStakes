package com.dev.carstakes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/carstakes")
public class CarStakesController {

    private final CarStakesService service;

    @Autowired
    public CarStakesController(CarStakesService service) {
        this.service = service;
    }

    /**
     *
     * @param car
     * @return
     */
    @GetMapping(value = {"/getstake", "/getstake/{car}"})
    public ResponseEntity<?> getStake(@PathVariable(required = false)  String car){
        ResponseEntity<?> resp;
        try {
            if (car == null || car.isBlank()) {
                // Якщо певне авто не вказане - видати інформацію по всім.
                Map<String, AtomicInteger> stakes = service.getAllStakes();
                resp= new ResponseEntity<Map>(stakes, HttpStatus.OK);
            } else {
                if( service.isCarValid(car)) {
                    resp= new ResponseEntity<String>("Stake for car "+ car+ " is:" + service.getStake(car.toUpperCase()),
                            HttpStatus.OK);
                } else {
                    resp = new ResponseEntity<String>("Error:Car is not valid! "+ car, HttpStatus.OK);
                }
            }

        }catch (Exception e) {
            //simple error response with status=200 ok
            resp = new ResponseEntity<String>("Error during getting a stake for the car!",
                    HttpStatus.OK);
        }
        return resp;
    }

    /**
     *
     * @param car
     * @param stake
     * @return
     */
    @PutMapping("/addstake/{car}/{stake}")
    public ResponseEntity<String> addStake(@PathVariable String car,@PathVariable Integer stake){
        ResponseEntity<String> resp;
        try {
            if (car == null || car.isBlank()) {
                resp = new ResponseEntity<String>("Error:Car is not given! "+ car, HttpStatus.OK);
            } else {
                System.out.println("stake=" + stake + "]");
                if( service.isCarValid(car)) {
                    if (stake != null && stake >0) {
                        service.addStake(car.toUpperCase(), stake);
                        resp = new ResponseEntity<String>("Stake " + stake +"$ was successfully added for car:"+ car, HttpStatus.OK);
                    } else {
                        resp = new ResponseEntity<String>("Error: Stake is not correct! "+ stake, HttpStatus.OK);
                    }
                } else {
                    resp = new ResponseEntity<String>("Error:Car is not valid! "+ car, HttpStatus.OK);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            //simple error response with status=200 ok
            resp = new ResponseEntity<String>("Error during adding a stake for the car!",
                    HttpStatus.OK);
        }
        return resp;
    }

}



