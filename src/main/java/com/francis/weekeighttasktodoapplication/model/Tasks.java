package com.francis.weekeighttasktodoapplication.model;


import lombok.Data;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private long duration;

    @Column(name = "start_time")
    private LocalTime startTime = LocalTime.now();

    @Column(name = "end_time")
    private LocalTime endTime = startTime.plusMinutes(getDuration());

    @ManyToOne
    private Users user;

    @ManyToOne
    @JoinColumn(name = "category_task")
    private Category category;


}
