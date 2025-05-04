package com.interview.fling.strategy.impl;

import com.interview.fling.model.Seat;
import com.interview.fling.strategy.PriceCalculatorStrategy;

import java.time.LocalDateTime;

public class SalmanKhanMovieStrategy extends PriceCalculatorStrategy {

    private static SalmanKhanMovieStrategy instance;
    public static SalmanKhanMovieStrategy getInstance() {
        if (instance == null) {
            instance = new SalmanKhanMovieStrategy();
        }
        return instance;
    }

    private SalmanKhanMovieStrategy() {}

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
                return 100;
            case PREMIUM:
                return 50;
            case NORMAL:
            default:
                return 20;
        }
    }

    private Integer updateWeekDayPrice(Seat seat) {
        switch (seat.getSeatType()) {
            case RECLINER:
                return 50;
            case PREMIUM:
                return 20;
            case NORMAL:
            default:
                return 10;
        }
    }
}
