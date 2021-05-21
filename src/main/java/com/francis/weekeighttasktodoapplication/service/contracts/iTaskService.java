package com.francis.weekeighttasktodoapplication.service.contracts;

import com.francis.weekeighttasktodoapplication.model.Tasks;

import java.util.List;

public interface iTaskService {
    List<Tasks> findAllByCategory(Long id);
    void saveNewTask(Tasks task, Long categoryId);
    void delete(Tasks tasks, Long categoryId);
    Tasks getTask(Long id);
    void updateTask(Long taskId, Tasks task);
}
