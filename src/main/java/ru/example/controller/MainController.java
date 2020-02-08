package ru.filin.HavachMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class MainController {
    @GetMapping
    public String getIndex(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", null);
        model.addAttribute("frontedData", data);
        return "index";
    }
}
