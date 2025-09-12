package com.ddebartolo.cinema_scheduler.service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ddebartolo.cinema_scheduler.model.CinemaRoom;
import com.ddebartolo.cinema_scheduler.model.Movie;
import com.ddebartolo.cinema_scheduler.model.Schedule;
import com.ddebartolo.cinema_scheduler.repository.CinemaRoomRepository;
import com.ddebartolo.cinema_scheduler.repository.MovieRepository;
import com.ddebartolo.cinema_scheduler.repository.ScheduleRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Service
public class ImportService {

     @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;
    
    public String importMoviesFromExcel(MultipartFile file, boolean createMovies, boolean createRooms) {
        StringBuilder log = new StringBuilder();
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header
                try {
                    ImportData data = parseRow(row, formatter);
                    Movie movie = findOrCreateMovie(data.movieTitle, createMovies);
                    CinemaRoom room = findOrCreateRoom(data.roomName, createRooms);

                    if (movie == null || room == null) {
                        log.append("Row ").append(row.getRowNum() + 1)
                           .append(": Movie or Room not found and creation not allowed.\n");
                        continue;
                    }

                    createSchedule(movie, room, data.startDate, data.endDate);
                } catch (Exception ex) {
                    log.append("Row ").append(row.getRowNum() + 1)
                       .append(": Error - ").append(ex.getMessage()).append("\n");
                }
            }
        } catch (Exception e) {
            log.append("General error: ").append(e.getMessage());
        }
        return log.length() == 0 ? "Import successful" : log.toString();
    }

    private ImportData parseRow(Row row, DateTimeFormatter formatter) {
        String movieTitle = row.getCell(0).getStringCellValue();
        String roomName = row.getCell(1).getStringCellValue();
        String startDateStr = row.getCell(2).getStringCellValue();
        String endDateStr = row.getCell(3).getStringCellValue();
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        return new ImportData(movieTitle, roomName, startDate, endDate);
    }

    private Movie findOrCreateMovie(String title, boolean create) {
        Optional<Movie> movieOpt = movieRepository.findByTitle(title);
        if (movieOpt.isPresent()) {
            return movieOpt.get();
        }
        if (create) {
            Movie m = new Movie();
            m.setTitle(title);
            // Set other fields if needed
            return movieRepository.save(m);
        }
        return null;
    }

    private CinemaRoom findOrCreateRoom(String name, boolean create) {
        Optional<CinemaRoom> roomOpt = cinemaRoomRepository.findByName(name);
        if (roomOpt.isPresent()) {
            return roomOpt.get();
        }
        if (create) {
            CinemaRoom r = new CinemaRoom();
            r.setName(name);
            // Set other fields if needed
            return cinemaRoomRepository.save(r);
        }
        return null;
    }

    private void createSchedule(Movie movie, CinemaRoom room, LocalDate start, LocalDate end) {
        Schedule schedule = new Schedule();
        schedule.setMovie(movie);
        schedule.setRoom(room);
        schedule.setStartDate(start);
        schedule.setEndDate(end);
        scheduleRepository.save(schedule);
    }

    private static class ImportData {
        String movieTitle;
        String roomName;
        LocalDate startDate;
        LocalDate endDate;

        ImportData(String movieTitle, String roomName, LocalDate startDate, LocalDate endDate) {
            this.movieTitle = movieTitle;
            this.roomName = roomName;
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }
}
