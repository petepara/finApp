package com.andersenlab.atink.repository;

import com.andersenlab.atink.model.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;
    @BeforeEach
    public void setup() {
        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMIN"));
    }
    @AfterEach
    public void destroyAll(){
        roleRepository.deleteAll();
    }
    @Test
    void findByName() {
        Role role = roleRepository.findByName("ROLE_USER").get();
        Assertions.assertThat(role.getName()).isEqualTo("ROLE_USER");
    }
    @Test
    void saveUserProfile(){
        roleRepository.save(new Role(3L, "ROLE_MODERATOR"));
        assertTrue(roleRepository.findByName("ROLE_MODERATOR").isPresent());
    }
    @Test
    void returnNullWhenDeleteRole(){
        roleRepository.delete(roleRepository.findByName("ROLE_USER").get());
        assertTrue(roleRepository.findByName("ROLE_USER").isEmpty());
    }

}