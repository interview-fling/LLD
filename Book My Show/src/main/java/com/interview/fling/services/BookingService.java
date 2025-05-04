package com.interview.fling.services;

import com.interview.fling.exception.InvalidCartException;
import com.interview.fling.exception.SomeOtherBookingInProgressException;
import com.interview.fling.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    private static BookingService instance;

    public static BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    private Map<Integer, Booking> bookingMap;
    private Map<Integer, Cart> cartMap;
    private final ShowService showService;
    private final TheatreService theatreService;

    private int bookingCount;
    private int cartCount;

    private BookingService() {
        bookingCount = 0;
        cartCount = 0;
        cartMap = new HashMap<>();
        bookingMap = new HashMap<>();
        showService = ShowService.getInstance();
        theatreService = TheatreService.getInstance();
    }

    public Cart initiateBooking(int showId, List<String> showSeatList, int userId) {
        cartCount++;
        MovieShow movieShow = showService.getShow(showId); // Do some validations
        List<ShowSeat> selectedShowSeat = getShowSeatsFromIds(showSeatList, movieShow);
        cartMap.put(cartCount, new Cart(selectedShowSeat, showId, userId, cartCount));
        return cartMap.get(cartCount);
    }

    public Booking completeBooking(int cartId, int paymentId, PaymentMethod paymentMethod) {
        if (!cartMap.containsKey(cartId)) {
            throw new InvalidCartException();
        }
        Cart cart = cartMap.get(cartId);
        MovieShow movieShow = showService.getShow(cart.getShowId());
        // Validate with payment gateway if everything is correct
        bookingCount++;
        updateSeatStatus(movieShow, cart);
        Booking booking = new Booking(bookingCount, paymentId, paymentMethod, cart.getShowSeatList(), cart.getShowId(), cart.getUserId(), cart.getId());
        cartMap.remove(cartId);
        return booking;
    }

    private List<ShowSeat> getShowSeatsFromIds(List<String> seatIds, MovieShow movieShow) {
        Map<String, ShowSeat> showSeats = movieShow.getShowSeatMap();
        List<ShowSeat> selectedShowSeats = new ArrayList<>();
        for (String seatId : seatIds) {
            if (showSeats.get(seatId).getStatus() != SeatStatus.VACANT) {
                throw new SomeOtherBookingInProgressException();
            }
        }
        for (String seatId : seatIds) {
            selectedShowSeats.add(showSeats.get(seatId));
            showSeats.get(seatId).setStatus(SeatStatus.LOCKED);
        }
        return selectedShowSeats;
    }

    private void updateSeatStatus(MovieShow movieShow, Cart cart) {
        Map<String, ShowSeat> allShowSeats = movieShow.getShowSeatMap();
        for (ShowSeat showSeat : cart.getShowSeatList()) {
            allShowSeats.get(showSeat.getSeatNumber()).setStatus(SeatStatus.OCCUPIED);
        }
    }
}
