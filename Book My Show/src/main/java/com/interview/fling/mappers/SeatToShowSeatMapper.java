package com.interview.fling.mappers;

import com.interview.fling.model.Seat;
import com.interview.fling.model.SeatStatus;
import com.interview.fling.model.ShowSeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatToShowSeatMapper {
    public static Map<String, ShowSeat> mapSeatToShowSeat(List<Seat> seats) {
        Map<String, ShowSeat> showSeatMap = new HashMap<>();
        for (Seat seat : seats) {
            showSeatMap.put(seat.getSeatNumber(), new ShowSeat(0, SeatStatus.VACANT, seat.getId(), seat.getScreenId(), seat.getSeatType(), seat.getSeatNumber()));
        }
        return showSeatMap;
    }
}
