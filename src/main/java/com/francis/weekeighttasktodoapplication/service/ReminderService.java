//package com.francis.weekeighttasktodoapplication.service;
//
//import com.francis.weekeighttasktodoapplication.model.Category;
//import com.francis.weekeighttasktodoapplication.model.Reminder;
//import com.francis.weekeighttasktodoapplication.model.Tasks;
//import com.francis.weekeighttasktodoapplication.model.Users;
//import com.francis.weekeighttasktodoapplication.repository.ReminderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReminderService {
//
//    @Autowired
//    private ReminderRepository reminderRepository;
//
//
//    @Scheduled(cron = "0 */1 */3 * */1 *")
//    public void giveReminder(){
//
//        Tasks task = new Tasks();
//        Reminder reminder = new Reminder();
//        reminder.setTasks(task);
//        reminder.setMessage("Do your task");
//        reminderRepository.save(reminder);
//        System.out.println("add service call");
//    }
//
//}
