package com.ddebartolo.cinema_scheduler.controller;

import com.ddebartolo.cinema_scheduler.dto.ScheduleDTO;
import com.ddebartolo.cinema_scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Schedule", description = "API di gestione programmazioni")
@RestController
@RequestMapping("/schedule")
@PreAuthorize("isAuthenticated()") 
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Public endpoints
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/page")
    public Page<ScheduleDTO> getScheduleDTOsPage(Pageable pageable) {
        return scheduleService.getSchedulesPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleDTOById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ScheduleDTO addScheduleDTO(@RequestBody ScheduleDTO ScheduleDTO) {
        return scheduleService.addSchedule(ScheduleDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateScheduleDTO(@PathVariable Long id, @RequestBody ScheduleDTO ScheduleDTO) {
        return scheduleService.updateSchedule(id, ScheduleDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleDTO(@PathVariable Long id) {
        if (scheduleService.deleteSchedule(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}