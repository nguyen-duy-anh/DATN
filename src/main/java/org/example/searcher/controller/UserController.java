package org.example.searcher.controller;

import org.example.searcher.entity.Role;
import org.example.searcher.entity.User;
import org.example.searcher.repository.RoleRepository;
import org.example.searcher.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String getUsersPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                               @RequestParam(required = false, defaultValue = "10") Integer size,
                               Model model) {
        Page<User> pageData = userService.getAllUsers(page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "admin/user/index";
    }

    @GetMapping("/create")
    public String getUserCreatePage(Model model) {
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("roleList", roleList);
        return "admin/user/create";
    }
}
