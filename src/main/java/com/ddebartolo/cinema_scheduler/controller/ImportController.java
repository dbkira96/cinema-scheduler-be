package com.ddebartolo.cinema_scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.ddebartolo.cinema_scheduler.service.ImportService;

@Tag(name = "TBD Import", description = "API di importazione dati (admin only)")
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/management")
public class ImportController {

    @Autowired
    private  ImportService importService ;
    
    
    @PostMapping("/import")
    @Operation(
        summary = "Importazione da Excel (endpoint da definire)",
        description = "Questo endpoint è in fase di definizione e potrebbe cambiare. Non utilizzare in produzione.",
        deprecated = true
    )
    public ResponseEntity<String> importExcel(
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "createMovies", defaultValue = "false") boolean createMovies,
            @RequestParam(name = "createRooms", defaultValue = "false") boolean createRooms) {

        String result = importService.importMoviesFromExcel(file, createMovies, createRooms);
        return ResponseEntity.ok(result);
    }
}
