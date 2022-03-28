package com.nms.uoc.controller;

import com.nms.uoc.service.impl.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class UserEntityController {
    @Autowired
    UserEntityService service;


    @GetMapping("/init")
    ResponseEntity getById() {
        service.init();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(service.getByID(id));
        return null;
    }
//
//    @GetMapping("/login")
//    ResponseEntity login(@RequestParam String username, @RequestParam String password) {
//
//        return null;
//    }

}
