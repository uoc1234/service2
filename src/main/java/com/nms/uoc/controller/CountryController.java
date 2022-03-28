package com.nms.uoc.controller;

import com.nms.uoc.contain.ServiceContext;
import com.nms.uoc.model.RequestDTO.CountryRequestDTO;
import com.nms.uoc.model.entity.Country;
import com.nms.uoc.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/country")
public class CountryController {
    @Autowired
    ICountryService service;

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

    @PostMapping("/create")
    ResponseEntity create(@RequestBody CountryRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(requestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.delete(id));
    }

    @PutMapping
    ResponseEntity update(@RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(country));
    }
}
