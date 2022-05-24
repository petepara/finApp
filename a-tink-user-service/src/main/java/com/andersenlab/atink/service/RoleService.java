package com.andersenlab.atink.service;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.Role;

public interface RoleService {
    Role saveRole(Role role) throws ResourceNotFoundException;
}
