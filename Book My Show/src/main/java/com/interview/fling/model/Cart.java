package com.interview.fling.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Cart {
    Integer id;
    Integer userId;
    Integer showId;
    List<ShowSeat> showSeatList;
    double amount;

    public Cart(List<ShowSeat> showSeatList, Integer showId, Integer userId, Integer id) {
        this.showSeatList = showSeatList;
        this.showId = showId;
        this.userId = userId;
        this.id = id;
        this.amount = showSeatList.stream().mapToDouble(ShowSeat::getPrice).sum();
    }
}
