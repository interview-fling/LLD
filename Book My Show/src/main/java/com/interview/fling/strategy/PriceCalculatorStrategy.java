package com.interview.fling.strategy;

import com.interview.fling.model.Seat;
import com.interview.fling.model.ShowSeat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public abstract class PriceCalculatorStrategy {

    abstract protected Integer addSeatingPrice(LocalDateTime localDateTime, Seat s);

    public void calculatePrice(LocalDateTime localDateTime, Map<String, ShowSeat> showSeats) {
        for (ShowSeat s : showSeats.values()) {
            s.addPrice(addSeatingPrice(localDateTime, s));
        }
    }
}
