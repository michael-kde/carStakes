package com.dev.carstakes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CarStakesService {

    private final CarStakesRepository repository;

    @Autowired
    public CarStakesService(CarStakesRepository repository) {
        this.repository = repository;
    }

    public Map<String, AtomicInteger> getAllStakes()  {
        return repository.getAllStakes();
    }

    public Integer getStake(String car) {
        return repository.getStake(car);
    }

    public Integer addStake(String car, Integer stake) {
        return repository.addStake(car,stake);
    }

    public boolean isCarValid(String car) {
        return repository.isCarValid(car);
    }
}