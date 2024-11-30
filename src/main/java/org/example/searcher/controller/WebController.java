package org.example.searcher.controller;

import org.example.searcher.service.RegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    private RegulationService regulationService;
    @GetMapping("/home")
    public String home() {
        return "admin/user/index";
    }

    @GetMapping("/regulations")
    public String regulations(Model model) {
        model.addAttribute("regulations", regulationService.getAll());
        return "web/regulation";
    }

    @GetMapping("/property")
    public String property(Model model) {
//        model.addAttribute("property", regulationService.getAll());
        return "web/property";
    }
}
