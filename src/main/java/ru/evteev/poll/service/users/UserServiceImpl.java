package ru.evteev.poll.service.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.User;
import ru.evteev.poll.repository.users.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    @Transactional
    public void createOrUpdateUser(User user) {
        userRepository.createOrUpdateUser(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userRepository.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }
}
