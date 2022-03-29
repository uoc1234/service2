package com.nms.uoc.controller;

import com.nms.uoc.contain.ServiceContext;
import com.nms.uoc.model.RequestDTO.ClubRequestDTO;
import com.nms.uoc.model.entity.Club;
import com.nms.uoc.service.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/club")
public class ClubController {
    @Autowired
    IClubService service;

    @PostMapping("/search")
    ResponseEntity search(@RequestBody ServiceContext serviceContext) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.search(serviceContext));
    }

    @GetMapping("/{id}")
    ResponseEntity getById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getByID(id));
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ok","ok3");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(map);
    }

    @PostMapping("/create")
    ResponseEntity create(@RequestBody ClubRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(requestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.delete(id));
    }

    @PutMapping
    ResponseEntity update(@RequestBody Club club) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(club));
    }
}
