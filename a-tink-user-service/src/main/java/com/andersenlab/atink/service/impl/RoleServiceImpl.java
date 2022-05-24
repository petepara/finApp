package com.andersenlab.atink.service.impl;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.Role;
import com.andersenlab.atink.repository.RoleRepository;
import com.andersenlab.atink.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role) throws ResourceNotFoundException {
        Optional<Role> savedRole = roleRepository.findByName(role.getName());
        if(savedRole.isPresent()){
            throw new ResourceNotFoundException("Such role already exists ");
        }
        return roleRepository.save(role);
    }
}
