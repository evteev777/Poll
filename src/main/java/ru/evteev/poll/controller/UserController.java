package ru.evteev.poll.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evteev.poll.dto.api.respomce.UserDTO;
import ru.evteev.poll.dto.mapper.UserMapper;
import ru.evteev.poll.entity.User;
import ru.evteev.poll.exception.FieldValidationException;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private static final String NO_SUCH_PERSON = "No such user with ID=%s in database";
    private static final String DELETED = "User with ID=%s is deleted";

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDTO> getUserList() {
        return userService.getAllUsers().stream()
                .map(UserMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        User user = userService.getUser(id);
        return UserMapper.INSTANCE.toDTO(user);
    }

    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody User user, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        throwExceptionIfValidationFails(br);
        userService.createOrUpdateUser(user);
        return UserMapper.INSTANCE.toDTO(user);
    }

    @PutMapping("/users")
    public UserDTO updateUser(@Valid @RequestBody User user, BindingResult br) {
        throwExceptionIfValidationFails(br);
        userService.createOrUpdateUser(user);
        return UserMapper.INSTANCE.toDTO(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        userService.deleteUser(id);
        return String.format(DELETED, id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_PERSON, id));
        }
    }

    private void throwExceptionIfValidationFails(BindingResult br) {
        if (br.hasErrors()) {
            throw new FieldValidationException(br.getAllErrors().toString());
        }
    }
}
