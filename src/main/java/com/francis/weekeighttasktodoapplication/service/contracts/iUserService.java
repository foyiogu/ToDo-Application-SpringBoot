package com.francis.weekeighttasktodoapplication.service.contracts;

import com.francis.weekeighttasktodoapplication.model.Users;

public interface iUserService {
    void registerNewUser(Users user);
    Users getUser(String email, String password);
    Users getUserById(Long id);
}
