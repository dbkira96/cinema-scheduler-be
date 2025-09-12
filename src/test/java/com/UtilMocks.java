package com;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import com.ddebartolo.cinema_scheduler.model.CinemaRoom;
import com.ddebartolo.cinema_scheduler.model.Movie;
import com.ddebartolo.cinema_scheduler.model.Schedule;

public  class UtilMocks {

    public static List<Schedule> getMockSchedules() {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setTitle("Evangelion: 3.0+1.0");
        movie1.setDuration(96);
        movie1.setGenre("Action");

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setTitle("Perfect Blue");
        movie2.setDuration(120);
        movie2.setGenre("Psychological Thriller");

        CinemaRoom room1 = new CinemaRoom();
        room1.setId(1L);
        room1.setName("Room 1");
        room1.setCapacity(100);

        CinemaRoom room2 = new CinemaRoom();
        room2.setId(2L);
        room2.setName("Room 2");
        room2.setCapacity(80);

        Schedule s1 = new Schedule();
        s1.setId(1L);
        s1.setStartDate(LocalDate.of(2025, 9, 10));
        s1.setMovie(movie1);
        s1.setTime(LocalTime.of(18, 30));
        s1.setRoom(room1);

        Schedule s2 = new Schedule();
        s2.setId(2L);
        s2.setStartDate(LocalDate.of(2025, 9, 11));
        s2.setMovie(movie2);
        s2.setTime(LocalTime.of(20, 0));
        s2.setRoom(room2);

        Schedule s3 = new Schedule();
        s3.setId(3L);
        s3.setStartDate(LocalDate.of(2025, 9, 12));
        s3.setMovie(movie1);
        s3.setRoom(room1);
        s3.setTime(LocalTime.of(20, 30));

        return Arrays.asList(s1, s2, s3);
    }
}