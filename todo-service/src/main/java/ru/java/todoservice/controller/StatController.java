package ru.java.todoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.java.entityservice.entity.Stat;
import ru.java.todoservice.service.StatService;

@RestController
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @PostMapping("/stat")
    public ResponseEntity<Stat> findByEmail(@RequestBody String userId) {
        return ResponseEntity.ok(statService.findStat(userId));
    }


}
