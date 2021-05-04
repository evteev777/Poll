package ru.evteev.poll.service;

import ru.evteev.poll.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void createOrUpdateUser(User user);

    User readUser(int id);

    void deleteUser(int id);
}
