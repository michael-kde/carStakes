package com.dev.carstakes;

import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CarStakesRepository {

    private final List<String> cars = Arrays.asList("FERRARI", "BMW", "AUDI", "HONDA");
    private final Map<String, AtomicInteger> carStakes = new HashMap<>();

    public CarStakesRepository() {
        for (String car: cars) {
            carStakes.put(car, new AtomicInteger(0));
        }

        System.out.println("CarStakesRepository map:" + carStakes);
    }

    public int getStake(String car) {
        return carStakes.get(car).intValue();
    }

    public Map<String, AtomicInteger> getAllStakes() {
        return carStakes;
    }

    public boolean isCarValid(String car) {
        return cars.contains(car);
    }

    public int addStake(String car, int stake) {
        AtomicInteger val = carStakes.get(car);
        int sum =val.addAndGet(stake);
        System.out.println("\t CarStakesRepository map after addStake:" + carStakes);
        return sum;
    }
}
