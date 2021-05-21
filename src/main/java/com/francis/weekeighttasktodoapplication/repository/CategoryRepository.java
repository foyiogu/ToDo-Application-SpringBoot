package com.francis.weekeighttasktodoapplication.repository;

import com.francis.weekeighttasktodoapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUserId(Long id);
    Category getCategoryById(Long categoryId);
}
