package com.francis.weekeighttasktodoapplication.service;

import com.francis.weekeighttasktodoapplication.model.Category;
import com.francis.weekeighttasktodoapplication.model.Tasks;
import com.francis.weekeighttasktodoapplication.repository.CategoryRepository;
import com.francis.weekeighttasktodoapplication.repository.TaskRepository;
import com.francis.weekeighttasktodoapplication.service.contracts.iTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService implements iTaskService {

    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(CategoryRepository categoryRepository, TaskRepository taskRepository) {
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Tasks> findAllByCategory(Long id){
        return taskRepository.findAllByCategoryId(id);
    }

    @Override
    public void saveNewTask(Tasks task, Long categoryId){
        Tasks tasksDB = taskRepository.save(task);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()){
            List<Tasks> tasks = categoryOptional.get().getTasks();
            tasks.add(tasksDB);
            categoryOptional.get().setTasks(tasks);
            categoryRepository.save(categoryOptional.get());
        }
    }

    @Override
    public void delete(Tasks tasks, Long categoryId){
        Category category = categoryRepository.getCategoryById(categoryId);
        category.getTasks().remove(tasks);
        taskRepository.delete(tasks);
    }

    @Override
    public Tasks getTask(Long id){
        return taskRepository.getTasksById(id);
    }

    @Override
    @Transactional
    public void updateTask(Long taskId, Tasks task) {
        Tasks taskDB = this.taskRepository.findById(taskId).orElseThrow(()->new IllegalStateException("does not exist"));
        String description = task.getDescription();
        long duration = task.getDuration();

        if (description != null && description.length()>0 && !Objects.equals(taskDB.getDescription(),description)){
            taskDB.setDescription(description);
            taskDB.setDuration(duration);
            taskDB.setStartTime(LocalTime.now());
            taskRepository.save(taskDB);
        }
    }

//    @Scheduled(fixedDelay = 5_000L)
//    public void giveReminder(){
//        Tasks task = new Tasks();
//        task.setReminder("Remember to do your task" + new Date().getSeconds());
//        taskRepository.save(task);
//        System.out.println("add service call");
//    }
//
////    @Scheduled(cron = "0/15 * * * * *")
////    public void fetchReminder(){
////        Category category = new Category();
////        List<Tasks> tasks = taskRepository.findAllByCategoryId(category.getId());
////        logger.info("tasks: {}",tasks);
////    }
}
