package com.sprint.hibernate.service.serviceImpl;

import com.sprint.hibernate.entity.Role;
import com.sprint.hibernate.repository.RoleRepository;
import com.sprint.hibernate.service.RoleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getRoleById(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No role /w id " + id)));
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
