package com.interview.fling.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeat extends Seat {
    int price;
    SeatStatus status;

    public ShowSeat(int price, SeatStatus status, Integer seatId, Integer screenId, SeatType seatType, String seatNumber) {
        super(seatId, screenId, seatType, seatNumber);
        this.price = price;
        this.status = status;
    }

    public void addPrice(int price) {
        this.price += price;
    }
}
