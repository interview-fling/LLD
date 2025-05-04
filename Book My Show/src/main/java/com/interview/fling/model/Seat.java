package com.interview.fling.model;

import lombok.Getter;

@Getter
public class Seat {
    Integer id;
    Integer screenId;
    SeatType seatType;
    String seatNumber;

    public Seat(Integer id, Integer screenId, SeatType seatType, String seatNumber) {
        this.id = id;
        this.screenId = screenId;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
    }
}
