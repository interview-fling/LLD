package com.interview.fling;

import com.interview.fling.model.*;
import com.interview.fling.services.BookingService;
import com.interview.fling.services.MovieService;
import com.interview.fling.services.ShowService;
import com.interview.fling.services.TheatreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Test
    public void test1() {
        BookingService bookingService = BookingService.getInstance();
        MovieService movieService = MovieService.getInstance();
        ShowService showService = ShowService.getInstance();
        TheatreService theatreService = TheatreService.getInstance();

        Integer theatreId = theatreService.addTheatre("Theatre 1");
        Integer screenId = theatreService.addScreen(theatreId, "Screen 1");
        theatreService.addSeat(screenId, SeatType.NORMAL, "A1");
        theatreService.addSeat(screenId, SeatType.NORMAL, "A2");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B1");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B2");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C1");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C2");

        Integer movieId = movieService.addMovie(Movie.builder().id(1).name("Movie 1").build());

        // Friday
        Integer showId = showService.addShow(screenId, movieId, LocalDateTime.of(2025, 5, 2, 12, 12, 12));

        // Select show seats
        List<String> showSeatIds = new ArrayList<>();
        showSeatIds.add("A1");
        showSeatIds.add("C1");
        Cart cart = bookingService.initiateBooking(showId, showSeatIds, 1);

        Booking booking = bookingService.completeBooking(cart.getId(), 1234324, PaymentMethod.CARD);


    }
}
