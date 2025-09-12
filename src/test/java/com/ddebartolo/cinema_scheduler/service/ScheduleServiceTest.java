package com.ddebartolo.cinema_scheduler.service;

import com.UtilMocks;
import com.ddebartolo.cinema_scheduler.dto.open.MovieScheduleDTO;
import com.ddebartolo.cinema_scheduler.model.Schedule;
import com.ddebartolo.cinema_scheduler.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ScheduleServiceTest {

    @MockitoBean
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Test
    void testGetMovieSchedulesByDateRange_returnsEvangelionWithTwoTimes() {
    List<Schedule> schedules = UtilMocks.getMockSchedules();

    Mockito.when(scheduleRepository.findByStartOrEndDateBetween(any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(schedules);

    List<MovieScheduleDTO> result = scheduleService.getMovieSchedulesByDateRange(
        LocalDate.of(2025, 9, 10), LocalDate.of(2025, 9, 12));

    // Dovrebbero esserci 2 MovieScheduleDTO (Evangelion, Perfect Blue)
    assertThat(result).hasSize(2);

    // Trova Evangelion
    MovieScheduleDTO evangelion = result.stream()
        .filter(dto -> dto.getTitle().toLowerCase().contains("evangelion"))
        .findFirst()
        .orElse(null);

    assertThat(evangelion).isNotNull();
    // Evangelion ha 1 RoomSchedule (Room 1)
    assertThat(evangelion.getRooms()).hasSize(1);
    // Room 1 ha 2 orari
    assertThat(evangelion.getRooms().get(0).getScheduledTimes()).hasSize(2);
    }

}
