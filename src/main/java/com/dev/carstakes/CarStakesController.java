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
     * Takes Invoice Object as input and returns save Status as ResponseEntity<String>
     */
/*    @PostMapping("/invoices")
    public ResponseEntity<String> saveInvoice(@RequestBody Invoice inv){
        ResponseEntity<String> resp = null;
        try{
            Long id = service.saveInvoice(inv);
            resp= new ResponseEntity<String>(
                    "Invoice '"+id+"' created",HttpStatus.CREATED); //201-created
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to save Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500-Internal Server Error
        }
        return resp;
    }
    */

    /**
     * To retrieve all Invoices, returns data retrieval Status as ResponseEntity<?>
     */
/*    @GetMapping("/getstake")
    public ResponseEntity<?> getAllInvoices() {
        ResponseEntity<?> resp=null;
        try {
            List<Invoice> list= service.getAllInvoices();
            resp= new ResponseEntity<List<Invoice>>(list,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get Invoice",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }*/

    /**
     * To retrieve one Invoice by providing id, returns Invoice object & Status as ResponseEntity<?>
     */
    @GetMapping(value = {"/getstake", "/getstake/{car}"})
    public ResponseEntity<?> getStake(@PathVariable(required = false)  String car){
        ResponseEntity<?> resp;
        try {
            System.out.println("car=" + car + "-");
            if (car == null || car.isBlank()) {
                // Якщо певне авто не вказане - видати інформацію по всім.
                Map<String, AtomicInteger> stakes = service.getAllStakes();
                resp= new ResponseEntity<Map>(stakes, HttpStatus.OK);
            } else {

                if( service.isCarValid(car)) {
                    Integer stake = service.getStake(car.toUpperCase());
                    resp= new ResponseEntity<String>("Stake for car "+ car+ " is:" + stake, HttpStatus.OK);
                } else {
                    resp = new ResponseEntity<String>("Error:Car is not valid! "+ car, HttpStatus.OK);
                }

            }

        }catch (Exception e) {
            System.out.println("Exception catched in /getStake" );
            e.printStackTrace();
            //simple error response with status=200 ok
            resp = new ResponseEntity<String>(e.getMessage(),
//                    "Error: Unable to find stakes!"
                    HttpStatus.OK);
        }
        return resp;
    }

//    @PutMapping("/addstake/{car}/{stake}")
    @GetMapping("/addstake/{car}/{stake}")
    public ResponseEntity<String> addStake(@PathVariable String car,@PathVariable Integer stake){
        ResponseEntity<String> resp= null;
        try {
            System.out.println("car=" + car + "]");

            if (car == null || car.isBlank()) {
                return new ResponseEntity<String>("Error:Car is not given! "+ car, HttpStatus.OK);
            } else {
                System.out.println("stake=" + stake + "]");

                if (stake != null && stake >0) {
                    Integer res = service.addStake(car.toUpperCase(), stake);
                    return new ResponseEntity<String>("Stake " + res +"$ was added for car:"+ car, HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("Error: Stake is not correct! "+ stake, HttpStatus.OK);
                }
            }

        }catch (Exception e) {
            System.out.println("Exception catched in contr addstake/" );
            e.printStackTrace();
            resp = new ResponseEntity<String>("Error during adding a stake for the car!",
                    HttpStatus.OK);
        }
        return resp;
    }

    @PutMapping("/addstake/{car}/{stake}")
    public ResponseEntity<String> addStake2(@PathVariable String car,@PathVariable Integer stake){
        ResponseEntity<String> resp;
        try {
            System.out.println("car=" + car + "]");

            if (car == null || car.isBlank()) {
                return new ResponseEntity<String>("Error:Car is not given! "+ car, HttpStatus.OK);
            } else {
                System.out.println("stake=" + stake + "]");
                if( service.isCarValid(car)) {
                    if (stake != null && stake >0) {
                        Integer res = service.addStake(car.toUpperCase(), stake);
                        resp = new ResponseEntity<String>("Stake " + res +"$ was successfully added for car:"+ car, HttpStatus.OK);
                    } else {
                        resp = new ResponseEntity<String>("Error: Stake is not correct! "+ stake, HttpStatus.OK);
                    }
                } else {
                    resp = new ResponseEntity<String>("Error:Car is not valid! "+ car, HttpStatus.OK);
                }
            }

        }catch (Exception e) {
            System.out.println("Exception catched in /addstake/" );
            e.printStackTrace();
            //simple error response with status=200 ok
            resp = new ResponseEntity<String>("Error during adding a stake for the car!",
                    HttpStatus.OK);
        }
        return resp;
    }

}



