package ru.evteev.poll.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evteev.poll.entity.Role;
import ru.evteev.poll.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository RoleRepository;

    @Override
    @Transactional
    public List<Role> getAllRole() {
        return RoleRepository.getAllRoles();
    }

    @Override
    @Transactional
    public void createOrUpdateRole(Role role) {
        RoleRepository.createOrUpdateRole(role);
    }

    @Override
    @Transactional
    public Role readRole(int id) {
        return RoleRepository.getRole(id);
    }

    @Override
    @Transactional
    public void deleteRole(int id) {
        RoleRepository.deleteRole(id);
    }
}
