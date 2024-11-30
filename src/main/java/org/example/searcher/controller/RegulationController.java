package org.example.searcher.controller;

import org.example.searcher.entity.Regulation;
import org.example.searcher.entity.Role;
import org.example.searcher.entity.User;
import org.example.searcher.service.RegulationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("admin/regulations")
public class RegulationController {
    private final RegulationService regulationService;

    public RegulationController(RegulationService regulationService) {
        this.regulationService = regulationService;
    }

    @GetMapping
    public String getRegulationPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "10") Integer size,
                                    Model model) {
        Page<Regulation> pageData = regulationService.findAll(page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "admin/regulation/index";
    }

    @GetMapping("/create")
    public String getRegulationCreatePage() {
        return "admin/regulation/create";
    }
}
