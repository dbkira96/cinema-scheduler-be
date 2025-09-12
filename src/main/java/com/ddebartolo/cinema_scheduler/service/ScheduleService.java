package com.ddebartolo.cinema_scheduler.service;

import com.ddebartolo.cinema_scheduler.dto.ScheduleDTO;
import com.ddebartolo.cinema_scheduler.dto.open.DayScheduleDto;
import com.ddebartolo.cinema_scheduler.dto.open.MovieScheduleDTO;
import com.ddebartolo.cinema_scheduler.dto.open.RoomScheduleDTO;
import com.ddebartolo.cinema_scheduler.model.Schedule;
import com.ddebartolo.cinema_scheduler.model.Movie;
import com.ddebartolo.cinema_scheduler.model.CinemaRoom;
import com.ddebartolo.cinema_scheduler.repository.ScheduleRepository;
import com.ddebartolo.cinema_scheduler.repository.MovieRepository;
import com.ddebartolo.cinema_scheduler.repository.CinemaRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CinemaRoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Page<ScheduleDTO> getSchedulesPage(Pageable pageable) {
        return scheduleRepository.findAll(pageable)
                .map(this::toDTO);
    }

    public Optional<ScheduleDTO> getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .map(this::toDTO);
    }

    public ScheduleDTO addSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = toEntity(scheduleDTO);
        Schedule saved = scheduleRepository.save(schedule);
        return toDTO(saved);
    }

    public Optional<ScheduleDTO> updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        return scheduleRepository.findById(id).map(schedule -> {
            schedule.setMovie(getMovie(scheduleDTO.getMovie().getId()));
            schedule.setRoom(getRoom(scheduleDTO.getRoom().getId()));
            schedule.setStartDate(scheduleDTO.getStartDate());
            schedule.setEndDate(scheduleDTO.getEndDate());
            schedule.setTime(scheduleDTO.getTime());
            Schedule updated = scheduleRepository.save(schedule);
            return toDTO(updated);
        });
    }

    public boolean deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<MovieScheduleDTO> getMovieSchedulesByDateRange(LocalDate start, LocalDate end) {
        List<Schedule> schedules = scheduleRepository.findByStartOrEndDateBetween(start, end);

        Map<Long, MovieScheduleDTO> movieMap = createMovieScheduleMapping(schedules);

        return new ArrayList<>(movieMap.values());
    }

    public List<DayScheduleDto> getMovieSchedulesGroupedByDay(LocalDate start, LocalDate end) {
        List<DayScheduleDto> result = new ArrayList<>();
        if (start == null || end == null || end.isBefore(start)) return result;

        List<Schedule> schedules = scheduleRepository.findByStartOrEndDateBetween(start, end);

        LocalDate date = start;
        while (!date.isAfter(end)) {
            // Filtra le schedules attive in quel giorno
            LocalDate currentDate = date;
            List<Schedule> activeSchedules = schedules.stream()
                .filter(s -> s.getStartDate() != null && s.getEndDate() != null &&
                        !currentDate.isBefore(s.getStartDate()) && !currentDate.isAfter(s.getEndDate()))
                .toList();
            if (!activeSchedules.isEmpty()) {
                DayScheduleDto dayDto = new DayScheduleDto();
                dayDto.setDate(currentDate);
                dayDto.setSchedules(new ArrayList<>(createMovieScheduleMapping(activeSchedules).values()));
                result.add(dayDto);
            }
            date = date.plusDays(1);
        }
        return result;
    }

    private Map<Long, MovieScheduleDTO> createMovieScheduleMapping(List<Schedule> schedules) {
        // Raggruppa le schedules per Movie
        Map<Long, MovieScheduleDTO> movieMap = new HashMap<>();

        for (Schedule schedule : schedules) {
            Long movieId = schedule.getMovie().getId();
            MovieScheduleDTO movieDto = movieMap.computeIfAbsent(movieId, id -> {
                MovieScheduleDTO dto = new MovieScheduleDTO();
                dto.setMovieId(id);
                dto.setTitle(schedule.getMovie().getTitle());
                dto.setRooms(new ArrayList<>());
                return dto;
            });

            // In base alla schedulazione, aggiungiamo le informazioni della room e dell'orario
            // Cerca se la room è già presente
            // Se non è presente, aggiungila e poi aggiungi l'orario
            RoomScheduleDTO roomDto = movieDto.getRooms().stream()
                .filter(r -> r.getRoomId().equals(schedule.getRoom().getId()))
                .findFirst()
                .orElseGet(() -> {
                    RoomScheduleDTO newRoom = new RoomScheduleDTO();
                    newRoom.setRoomId(schedule.getRoom().getId());
                    newRoom.setRoomName(schedule.getRoom().getName());
                    newRoom.setScheduledTimes(new ArrayList<>());
                    movieDto.getRooms().add(newRoom);
                    return newRoom;
                });

            roomDto.getScheduledTimes().add(schedule.getTime());
        }
        return movieMap;
    }

    // Helper methods for mapping

    private ScheduleDTO toDTO(Schedule schedule) {
        ScheduleDTO dto = modelMapper.map(schedule, ScheduleDTO.class);
        
        return dto;
    }

    private Schedule toEntity(ScheduleDTO dto) {
        Schedule schedule = modelMapper.map(dto, Schedule.class);
        schedule.setMovie(getMovie(dto.getMovie().getId()));
        schedule.setRoom(getRoom(dto.getRoom().getId()));
        return schedule;
    }

    private Movie getMovie(Long movieId) {
        return movieId != null ? movieRepository.findById(movieId).orElse(null) : null;
    }

    private CinemaRoom getRoom(Long roomId) {
        return roomId != null ? roomRepository.findById(roomId).orElse(null) : null;
    }



}