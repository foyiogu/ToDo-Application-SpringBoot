package com.francis.weekeighttasktodoapplication.repository;

import com.francis.weekeighttasktodoapplication.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
