package ru.java.todoservice.service;

import org.springframework.stereotype.Service;
import ru.java.entityservice.entity.Category;
import ru.java.todoservice.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll(Long userId) {
        return repository.findByUserIdOrderByTitleAsc(userId);
    }


    public Category add(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {
        return repository.save(category);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Category> findByTitle(String text, String userId) {
        return repository.findByTitle(text, userId);
    }

    public Category findById(Long id) {
        return repository.findById(id).get();
    }
}
