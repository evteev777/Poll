package ru.evteev.poll.repository;

import ru.evteev.poll.entity.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> getAllRoles();

    void createOrUpdateRole(Role role);

    Role getRole(int id);

    void deleteRole(int id);
}
