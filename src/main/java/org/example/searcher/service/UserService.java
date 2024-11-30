package org.example.searcher.service;

import org.example.searcher.entity.User;
import org.example.searcher.exception.NotFoundException;
import org.example.searcher.model.request.UpsertUserRequest;
import org.example.searcher.repository.RoleRepository;
import org.example.searcher.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Page<User> getAllUsers(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("username").descending());
        return userRepository.findAll(pageable);
    }

    public User createUser(UpsertUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(roleRepository.findByIdIn(request.getRoles()));
        userRepository.save(user);
        return user;
    }

    public User updateUser(UpsertUserRequest request, Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(roleRepository.findByIdIn(request.getRoles()));
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user with id: " + id));
        userRepository.delete(user);
    }
}
