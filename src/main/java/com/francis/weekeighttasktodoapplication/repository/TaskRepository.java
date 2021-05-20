package com.francis.weekeighttasktodoapplication.repository;

import com.francis.weekeighttasktodoapplication.model.Category;
import com.francis.weekeighttasktodoapplication.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findAllByCategoryId(Long id);
    Tasks getTasksById(Long taskId);
}
