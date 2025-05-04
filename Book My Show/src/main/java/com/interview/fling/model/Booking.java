package com.interview.fling.model;

import java.util.List;

public class Booking extends Cart {

    PaymentMethod paymentMethod;
    Integer id;
    Integer paymentId;

    public Booking(Integer id, Integer paymentId, PaymentMethod paymentMethod, List<ShowSeat> showSeatList, Integer showId, Integer userId, Integer cartId) {
        super(showSeatList, showId, userId, cartId);
        this.id = id;
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
    }
}
