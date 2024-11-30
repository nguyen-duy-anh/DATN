package org.example.searcher;

import org.example.searcher.entity.Role;
import org.example.searcher.entity.User;
import org.example.searcher.repository.RoleRepository;
import org.example.searcher.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SearcherApplicationTests {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;



    @Test
    void contextLoads() {
    }
    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role(null, "ADMIN"),
                new Role(null, "USER")
        );

        roleRepository.saveAll(roles);
    }

    @Test
    void save_users() {
        Role userRole = roleRepository.findByName("USER").orElse(null);
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);

        List<User> users = List.of(
                new User(null, "Duy Anh","duyanh@gmail.com",passwordEncoder.encode("123"),   List.of(adminRole)),
                new User(null, "Van Hung","hung@gmail.com", passwordEncoder.encode("123"),  List.of(userRole))

        );
        userRepository.saveAll(users);
    }
}
