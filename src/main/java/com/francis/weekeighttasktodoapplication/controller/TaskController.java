package com.francis.weekeighttasktodoapplication.controller;

import com.francis.weekeighttasktodoapplication.model.Category;
import com.francis.weekeighttasktodoapplication.model.Tasks;
import com.francis.weekeighttasktodoapplication.model.Users;
import com.francis.weekeighttasktodoapplication.service.CategoryService;
//import com.francis.weekeighttasktodoapplication.service.ReminderService;
import com.francis.weekeighttasktodoapplication.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final CategoryService categoryService;
//    private final ReminderService reminderService;


    @Autowired
    public TaskController(TaskService taskService, CategoryService categoryService ) {
        this.taskService = taskService;
        this.categoryService = categoryService;
//        this.reminderService = reminderService;
    }

    @GetMapping("/task/{categoryId}")
    public String getTaskById(@PathVariable("categoryId") Long categoryId, Model model, HttpSession session){
        Users user = (Users) session.getAttribute("user_session");

        session.setAttribute("id",categoryId);
        model.addAttribute("catName", categoryService.getCategoryById(categoryId).getCategoryName());
        model.addAttribute("user", user);
        model.addAttribute("newTask", new Tasks());
        model.addAttribute("allTasks", taskService.findAllByCategory(categoryId));
        return "tasks";
    }

    @PostMapping("/addTask")
    public String addNewTask( HttpSession session, @ModelAttribute("newTask") Tasks task, Model model){
        Long id = (Long) session.getAttribute("id");
        Category category = categoryService.getCategoryById(id);

        model.addAttribute("categoryName",category.getCategoryName());
        Users validUser = (Users) session.getAttribute("user_session");
        task.setStartTime(LocalTime.now());
        task.setEndTime(LocalTime.now().plusMinutes(task.getDuration()));
        task.setUser(validUser);
        task.setCategory(category);
//        reminderService.giveReminder();
        taskService.saveNewTask(task, category.getId());
        return "redirect:/task/" + category.getId();
    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable(name = "id") Long taskId, Model model) {
        Tasks task = taskService.getTask(taskId);
        model.addAttribute("newTask",task);
        return "edit";
    }

    @PostMapping("/update_task")
    public String editTask(@ModelAttribute("newTask") Tasks editedTask) {
        taskService.updateTask(editedTask.getId(),editedTask);
        Category category1 = taskService.getTask(editedTask.getId()).getCategory();
        return "redirect:/task/" + category1.getId();

    }

    @GetMapping("/deleteTask/{id}/{categoryID}") //CHANGED THE STATE BY REMOVING A FILE
    public String delete(@PathVariable(name = "id")Long id, @PathVariable(name = "categoryID")Long categoryId){
        Category category = categoryService.getCategoryById(categoryId);
        Tasks task = taskService.getTask(id);
        taskService.delete(task,category.getId());
        return "redirect:/task/" + category.getId();
    }
}
