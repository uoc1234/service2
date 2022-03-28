package com.nms.uoc.controller;

import com.nms.uoc.contain.ServiceContext;
import com.nms.uoc.service.IDrawnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/drawn")
public class DrawnController {

    @Autowired
    IDrawnService service;

    @PostMapping("/quarterfinals")
    ResponseEntity quarterfinals(@RequestBody int... clubId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.quarterfinals(clubId));
    }

    @PostMapping("/semifinal")
    ResponseEntity semifinal(@RequestBody int... clubId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.semifinal(clubId));
    }
}
