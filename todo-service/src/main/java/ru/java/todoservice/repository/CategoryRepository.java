package ru.java.todoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.java.entityservice.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserIdOrderByTitleAsc(Long id);

    @Query("SELECT c FROM Category c where " +
            "(:title is null or :title='' " +
            " or lower(c.title) like lower(concat('%', :title,'%'))) " +
            " and c.userId=:id  " +
            " order by c.title asc")

    List<Category> findByTitle(@Param("title") String title, @Param("id") String id);
}
