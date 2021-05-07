package ru.evteev.poll.repository.users;

import ru.evteev.poll.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    void createOrUpdateUser(User user);

    User getUser(int id);

    void deleteUser(int id);
}
