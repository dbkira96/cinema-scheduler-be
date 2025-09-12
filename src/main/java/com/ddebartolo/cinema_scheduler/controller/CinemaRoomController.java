package com.ddebartolo.cinema_scheduler.controller;

import com.ddebartolo.cinema_scheduler.dto.RoomDTO;
import com.ddebartolo.cinema_scheduler.repository.CinemaRoomRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Cinema Room", description = "API per consultare le sale cinema")
@RestController
@RequestMapping("/rooms")
public class CinemaRoomController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Operation(summary = "Lista di tutte le sale cinema")
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = cinemaRoomRepository.findAll().stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .toList();
        return ResponseEntity.ok(rooms);
    }
}
