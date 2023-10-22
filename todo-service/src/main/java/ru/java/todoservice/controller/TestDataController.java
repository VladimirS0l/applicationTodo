package ru.java.todoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.java.todoservice.service.TestDataService;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class TestDataController {

    private final TestDataService testDataService;

    @PostMapping("/init")
    public ResponseEntity<Boolean> init(@RequestBody String userId) {
        testDataService.initTestData(userId);
        return ResponseEntity.ok(true);

    }


}
