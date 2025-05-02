package com.interview.fling.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
public class MovieShow {
    Integer showId;
    Integer movieId;
    LocalDateTime startTime;
//    List<ShowSeat> showSeatList;
    Map<String, ShowSeat> showSeatMap;
    Integer screenId;

    public MovieShow(Integer showId, Integer movieId, Integer screenId, LocalDateTime startTime, Map<String, ShowSeat> showSeatMap) {
        this.showId = showId;
        this.movieId = movieId;
        this.screenId = screenId;
        this.startTime = startTime;
        this.showSeatMap = showSeatMap;
    }



}
