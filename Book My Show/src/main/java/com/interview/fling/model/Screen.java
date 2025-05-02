package com.interview.fling.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    @Getter
    Integer id;
    String name;
    Integer theatreId;
    @Getter
    List<Integer> seatIds;

    public Screen(Integer id, Integer theatreId, String name) {
        this.id = id;
        this.name = name;
        this.theatreId = theatreId;
        this.seatIds = new ArrayList<>();
    }
}
