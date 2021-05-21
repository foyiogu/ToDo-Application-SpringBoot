package com.francis.weekeighttasktodoapplication.model;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String categoryName;

    private LocalDateTime created_at = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tasks> tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_category_id")
    private Users user;
}
