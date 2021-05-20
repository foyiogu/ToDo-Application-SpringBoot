package com.francis.weekeighttasktodoapplication.controller;

import com.francis.weekeighttasktodoapplication.model.Category;
import com.francis.weekeighttasktodoapplication.model.Tasks;
import com.francis.weekeighttasktodoapplication.model.Users;
import com.francis.weekeighttasktodoapplication.repository.TaskRepository;
import com.francis.weekeighttasktodoapplication.service.CategoryService;
import com.francis.weekeighttasktodoapplication.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task/{categoryId}")
    public String getTaskById(@PathVariable("categoryId") Long categoryId, Model model, HttpSession session){
        Users user = (Users) session.getAttribute("user_session");
        session.setAttribute("id",categoryId);
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
        taskService.saveNewTask(task, category.getId());
        return "redirect:/task/" + category.getId();
    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable(name = "id") Long taskId, Model model, HttpSession session) {
        Users validUser = (Users) session.getAttribute("user_session");
        Tasks task = taskService.getTask(taskId);
        model.addAttribute("newTask",task);

        return "edit";

    }

    @PostMapping("/update_task")
    public String editTask(@ModelAttribute("newTask") Tasks editedTask) {
        taskService.updateTask(editedTask.getId(),editedTask);
//        Category category = editedTask.getCategory();
        Category category1 = taskService.getTask(editedTask.getId()).getCategory();
        return "redirect:/task/" + category1.getId();

    }

    @RequestMapping("/deleteTask/{id}/{categoryID}")
    public String delete(@PathVariable(name = "id")Long id, @PathVariable(name = "categoryID")Long categoryId){
        Category category = categoryService.getCategoryById(categoryId);
        Tasks task = taskService.getTask(id);
        taskService.delete(task,category.getId());
        return "redirect:/task/" + category.getId();
    }
}
