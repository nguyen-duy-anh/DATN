package org.example.searcher.controller;

import org.example.searcher.entity.Category;
import org.example.searcher.entity.Role;
import org.example.searcher.entity.User;
import org.example.searcher.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getCategoriesPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "10") Integer size,
                                    Model model) {
        Page<Category> pageData = categoryService.findAll(page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "admin/category/index";
    }

    @GetMapping("/create")
    public String getCategoryCreatePage() {
        return "admin/category/create";
    }
}
