package com.interview.fling.services;

import com.interview.fling.model.Screen;
import com.interview.fling.model.Seat;
import com.interview.fling.model.SeatType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class SeatService {
    private int seatCounter;
    @Getter
    private final Map<Integer, Seat> seatMap;

    protected SeatService() {
        this.seatCounter = 0;
        this.seatMap = new HashMap<>();
    }

    protected Integer addSeat(Integer screenId, SeatType seatType, String seatNumber) {
        seatCounter++;
        Seat seat = new Seat(seatCounter, screenId, seatType, seatNumber);
        seatMap.put(seatCounter, seat);
        return seatCounter;
    }
}
