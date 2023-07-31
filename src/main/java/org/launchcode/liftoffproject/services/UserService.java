package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.User;

public interface UserService {
    User findByUsername(String username);
    User findById(Long id);
    void saveUser(User user);
}
