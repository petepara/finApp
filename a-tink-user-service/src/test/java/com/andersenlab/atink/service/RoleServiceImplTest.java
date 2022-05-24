package com.andersenlab.atink.service;

import com.andersenlab.atink.exception.ResourceNotFoundException;
import com.andersenlab.atink.model.entity.Role;
import com.andersenlab.atink.repository.RoleRepository;
import com.andersenlab.atink.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;
    Role role;

    @BeforeEach
    public void setup() {
        role = new Role(1L, "ROLE_USER");
    }

    @Test
    void returnsRoleWhenItSaved() throws ResourceNotFoundException {
        doReturn(role).when(roleRepository).save(role);
        Role savedRole = roleService.saveRole(role);
        verify(roleRepository, times(1)).save(role);
        assertThat(savedRole).isNotNull();
        assertEquals(savedRole, role);
    }

    @Test
    void throwsExceptionWhenRoleExists() {
        doReturn(Optional.of(role)).when(roleRepository).findByName(role.getName());
        assertTrue(roleRepository.findByName(role.getName()).isPresent());
        assertThrows(ResourceNotFoundException.class, () -> {
            roleService.saveRole(role);
        });
        verify(roleRepository, never()).save(any(Role.class));
    }
}