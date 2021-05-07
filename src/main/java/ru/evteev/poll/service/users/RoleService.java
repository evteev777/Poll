package ru.evteev.poll.service.users;

import ru.evteev.poll.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    void createOrUpdateRole(Role role);

    Role getRole(int id);

    void deleteRole(int id);
}
