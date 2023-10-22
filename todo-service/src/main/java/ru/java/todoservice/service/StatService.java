package ru.java.todoservice.service;

import org.springframework.stereotype.Service;
import ru.java.entityservice.entity.Stat;
import ru.java.todoservice.repository.StatRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatService {

    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(String userId) {
        return repository.findByUserId(userId);
    }

}
