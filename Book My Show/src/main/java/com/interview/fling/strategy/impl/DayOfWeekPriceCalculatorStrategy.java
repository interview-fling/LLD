package com.interview.fling.strategy.impl;

import com.interview.fling.model.Seat;
import com.interview.fling.model.SeatType;
import com.interview.fling.strategy.PriceCalculatorStrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DayOfWeekPriceCalculatorStrategy extends PriceCalculatorStrategy {

    private static DayOfWeekPriceCalculatorStrategy instance;
    public static DayOfWeekPriceCalculatorStrategy getInstance() {
        if (instance == null) {
            instance = new DayOfWeekPriceCalculatorStrategy();
        }
        return instance;
    }

    private DayOfWeekPriceCalculatorStrategy() {}

    @Override
    protected Integer addSeatingPrice(LocalDateTime localDateTime, Seat seat) {
        switch (localDateTime.getDayOfWeek()) {
            case FRIDAY:
            case SUNDAY:
            case SATURDAY:
                return updateWeekendPrice(seat);
            default:
                return updateWeekDayPrice(seat);
        }
    }

    private Integer updateWeekendPrice(Seat seat) {
        switch (seat.getSeatType()) {
            case RECLINER:
                return 500;
            case PREMIUM:
                return 400;
            case NORMAL:
            default:
                return 300;
        }
    }

    private Integer updateWeekDayPrice(Seat seat) {
        switch (seat.getSeatType()) {
            case RECLINER:
                return 400;
            case PREMIUM:
                return 300;
            case NORMAL:
            default:
                return 200;
        }
    }
}
