package com.sprint.hibernate.service;


import com.sprint.hibernate.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);
    List<Role> getAll();
}
