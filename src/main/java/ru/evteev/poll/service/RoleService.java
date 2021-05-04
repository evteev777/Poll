package ru.evteev.poll.service;

import ru.evteev.poll.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    void createOrUpdateRole(Role role);

    Role readRole(int id);

    void deleteRole(int id);
}
