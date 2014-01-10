package com.home911.reviewed.service.user;

import com.home911.reviewed.model.User;

public interface UserService {
    public User getUser(String username);
    public void saveUser(User user);
}
