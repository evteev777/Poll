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
import ru.evteev.poll.dto.api.respomce.RoleDTO;
import ru.evteev.poll.dto.mapper.RoleMapper;
import ru.evteev.poll.entity.Role;
import ru.evteev.poll.exception.FieldValidationException;
import ru.evteev.poll.exception.NoSuchEntityException;
import ru.evteev.poll.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RoleController {

    private static final String NO_SUCH_PERSON = "No such role with ID=%s in database";
    private static final String DELETED = "Role with ID=%s is deleted";

    private final RoleService roleService;

    @GetMapping("/roles")
    public List<RoleDTO> getRoleList() {
        return roleService.getAllRole().stream()
                .map(RoleMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/roles/{id}")
    public RoleDTO getRole(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        Role role = roleService.getRole(id);
        return RoleMapper.INSTANCE.toDTO(role);
    }

    @PostMapping("/roles")
    public RoleDTO createRole(@Valid @RequestBody Role role, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        throwExceptionIfValidationFails(br);
        roleService.createOrUpdateRole(role);
        return RoleMapper.INSTANCE.toDTO(role);
    }

    @PutMapping("/roles")
    public RoleDTO updateRole(@Valid @RequestBody Role role, BindingResult br) {
        throwExceptionIfValidationFails(br);
        roleService.createOrUpdateRole(role);
        return RoleMapper.INSTANCE.toDTO(role);
    }

    @DeleteMapping("/roles/{id}")
    public String deleteRole(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        roleService.deleteRole(id);
        return String.format(DELETED, id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        Role role = roleService.getRole(id);
        if (role == null) {
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
