package com.interview.fling;

import com.interview.fling.exception.SomeOtherBookingInProgressException;
import com.interview.fling.model.*;
import com.interview.fling.services.BookingService;
import com.interview.fling.services.MovieService;
import com.interview.fling.services.ShowService;
import com.interview.fling.services.TheatreService;
import com.interview.fling.strategy.PriceCalculatorStrategy;
import com.interview.fling.strategy.impl.DayOfWeekPriceCalculatorStrategy;
import com.interview.fling.strategy.impl.SalmanKhanMovieStrategy;
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

        // Add theatre
        Integer theatreId = theatreService.addTheatre("Theatre 1");

        // Add screen inside theatre
        Integer screenId = theatreService.addScreen(theatreId, "Screen 1");

        // Add seats inside the screen
        theatreService.addSeat(screenId, SeatType.NORMAL, "A1");
        theatreService.addSeat(screenId, SeatType.NORMAL, "A2");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B1");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B2");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C1");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C2");

        // Add a movie
        Integer movieId = movieService.addMovie(Movie.builder().id(1).name("Movie 1").build());

        // Add a show for the movie
        Integer showId = showService.addShow(screenId, movieId, LocalDateTime.of(2025, 5, 2, 12, 12, 12));

        // Select show seats
        List<String> showSeatIds = new ArrayList<>();
        showSeatIds.add("A1");
        showSeatIds.add("C1");

        // Initiate a booking
        Cart cart = bookingService.initiateBooking(showId, showSeatIds, 1);

        // Create a booking
        Booking booking = bookingService.completeBooking(cart.getId(), 1234324, PaymentMethod.CARD);

        Assertions.assertEquals(700, booking.getAmount());
        Assertions.assertEquals(2, booking.getShowSeatList().size());

        Assertions.assertEquals(SeatType.NORMAL, booking.getShowSeatList().get(0).getSeatType());
        Assertions.assertEquals(300, booking.getShowSeatList().get(0).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(0).getScreenId());

        Assertions.assertEquals(SeatType.PREMIUM, booking.getShowSeatList().get(1).getSeatType());
        Assertions.assertEquals(400, booking.getShowSeatList().get(1).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(1).getScreenId());

        MovieShow movieShow = showService.getShow(showId);
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("A1").getStatus());
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("C1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("C2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("A2").getStatus());
    }


    @Test
    public void test2() {
        BookingService bookingService = BookingService.getInstance();
        MovieService movieService = MovieService.getInstance();
        ShowService showService = ShowService.getInstance();
        TheatreService theatreService = TheatreService.getInstance();

        // Add theatre
        Integer theatreId = theatreService.addTheatre("Theatre 2");

        // Add screen inside theatre
        Integer screenId = theatreService.addScreen(theatreId, "Screen 2");

        // Add seats inside the screen
        theatreService.addSeat(screenId, SeatType.NORMAL, "A1");
        theatreService.addSeat(screenId, SeatType.NORMAL, "A2");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B1");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B2");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C1");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C2");

        // Add a movie
        Integer movieId = movieService.addMovie(Movie.builder().id(1).name("Movie 2").build());

        // Add a show for the movie weekday show
        Integer showId = showService.addShow(screenId, movieId, LocalDateTime.of(2025, 5, 1, 12, 12, 12));

        // Select show seats
        List<String> showSeatIds = new ArrayList<>();
        showSeatIds.add("A1");
        showSeatIds.add("C1");

        // Initiate a booking
        Cart cart = bookingService.initiateBooking(showId, showSeatIds, 1);

        // Create a booking
        Booking booking = bookingService.completeBooking(cart.getId(), 1234324, PaymentMethod.CARD);

        Assertions.assertEquals(500, booking.getAmount());
        Assertions.assertEquals(2, booking.getShowSeatList().size());

        Assertions.assertEquals(SeatType.NORMAL, booking.getShowSeatList().get(0).getSeatType());
        Assertions.assertEquals(200, booking.getShowSeatList().get(0).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(0).getScreenId());

        Assertions.assertEquals(SeatType.PREMIUM, booking.getShowSeatList().get(1).getSeatType());
        Assertions.assertEquals(300, booking.getShowSeatList().get(1).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(1).getScreenId());

        MovieShow movieShow = showService.getShow(showId);
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("A1").getStatus());
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("C1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("C2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("A2").getStatus());
    }

    @Test
    public void test3() {
        BookingService bookingService = BookingService.getInstance();
        MovieService movieService = MovieService.getInstance();
        ShowService showService = ShowService.getInstance();
        TheatreService theatreService = TheatreService.getInstance();

        // Add theatre
        Integer theatreId = theatreService.addTheatre("Theatre 3");

        // Add screen inside theatre
        Integer screenId = theatreService.addScreen(theatreId, "Screen 3");

        // Add seats inside the screen
        theatreService.addSeat(screenId, SeatType.NORMAL, "A1");
        theatreService.addSeat(screenId, SeatType.NORMAL, "A2");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B1");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B2");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C1");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C2");

        // Add a movie
        Integer movieId = movieService.addMovie(Movie.builder().id(1).name("Movie 3").build());

        // Add a show for the movie weekday show
        Integer showId = showService.addShow(screenId, movieId, LocalDateTime.of(2025, 5, 1, 12, 12, 12));

        // Select show seats
        List<String> showSeatIds = new ArrayList<>();
        showSeatIds.add("A1");
        showSeatIds.add("C1");

        // Initiate a booking
        Cart cart = bookingService.initiateBooking(showId, showSeatIds, 1);

        // Someone else tries to book the same seat
        Assertions.assertThrows(SomeOtherBookingInProgressException.class, () -> {
            Cart cart2 = bookingService.initiateBooking(showId, showSeatIds, 2);
        });

        // Create a booking
        Booking booking = bookingService.completeBooking(cart.getId(), 1234324, PaymentMethod.CARD);

        Assertions.assertEquals(500, booking.getAmount());
        Assertions.assertEquals(2, booking.getShowSeatList().size());

        Assertions.assertEquals(SeatType.NORMAL, booking.getShowSeatList().get(0).getSeatType());
        Assertions.assertEquals(200, booking.getShowSeatList().get(0).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(0).getScreenId());

        Assertions.assertEquals(SeatType.PREMIUM, booking.getShowSeatList().get(1).getSeatType());
        Assertions.assertEquals(300, booking.getShowSeatList().get(1).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(1).getScreenId());

        MovieShow movieShow = showService.getShow(showId);
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("A1").getStatus());
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("C1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("C2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("A2").getStatus());
    }

    @Test
    public void test4() {
        BookingService bookingService = BookingService.getInstance();
        MovieService movieService = MovieService.getInstance();
        ShowService showService = ShowService.getInstance();
        TheatreService theatreService = TheatreService.getInstance();

        // Add theatre
        Integer theatreId = theatreService.addTheatre("Theatre 1");

        // Add screen inside theatre
        Integer screenId = theatreService.addScreen(theatreId, "Screen 1");

        // Add seats inside the screen
        theatreService.addSeat(screenId, SeatType.NORMAL, "A1");
        theatreService.addSeat(screenId, SeatType.NORMAL, "A2");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B1");
        theatreService.addSeat(screenId, SeatType.RECLINER, "B2");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C1");
        theatreService.addSeat(screenId, SeatType.PREMIUM, "C2");

        // Add a movie
        Integer movieId = movieService.addMovie(Movie.builder().id(1).name("Movie 1").build());

        // Add a show for the movie
        List<PriceCalculatorStrategy> strategyList = new ArrayList<>();
        strategyList.add(DayOfWeekPriceCalculatorStrategy.getInstance());
        strategyList.add(SalmanKhanMovieStrategy.getInstance());
        Integer showId = showService.addShow(screenId, movieId, LocalDateTime.of(2025, 5, 2, 12, 12, 12), strategyList);

        // Select show seats
        List<String> showSeatIds = new ArrayList<>();
        showSeatIds.add("A1");
        showSeatIds.add("C1");

        // Initiate a booking
        Cart cart = bookingService.initiateBooking(showId, showSeatIds, 1);

        // Create a booking
        Booking booking = bookingService.completeBooking(cart.getId(), 1234324, PaymentMethod.CARD);

        Assertions.assertEquals(770, booking.getAmount());
        Assertions.assertEquals(2, booking.getShowSeatList().size());

        Assertions.assertEquals(SeatType.NORMAL, booking.getShowSeatList().get(0).getSeatType());
        Assertions.assertEquals(320, booking.getShowSeatList().get(0).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(0).getScreenId());

        Assertions.assertEquals(SeatType.PREMIUM, booking.getShowSeatList().get(1).getSeatType());
        Assertions.assertEquals(450, booking.getShowSeatList().get(1).getPrice());
        Assertions.assertEquals(1, booking.getShowSeatList().get(1).getScreenId());

        MovieShow movieShow = showService.getShow(showId);
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("A1").getStatus());
        Assertions.assertEquals(SeatStatus.OCCUPIED, movieShow.getShowSeatMap().get("C1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B1").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("B2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("C2").getStatus());
        Assertions.assertEquals(SeatStatus.VACANT, movieShow.getShowSeatMap().get("A2").getStatus());
    }

}
