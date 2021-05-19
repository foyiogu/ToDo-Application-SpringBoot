package com.francis.weekeighttasktodoapplication.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "First name required")
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = "Last name required")
    private String lastName;

    @Column(nullable = false)
    @NotEmpty(message = "email required")
    private String email;

    @Column
    @NotEmpty(message = "please select a gender")
    private String gender;

    @Column
    @NotEmpty(message = "Please input your date of birth")
    private String dob;

    @Column(nullable = false)
    @NotEmpty(message = "password required")
    @Size(min = 3, message = "password must be more than 3 characters")
    private String password;

    @OneToMany
    private List<Category> categories = new ArrayList<>();

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
