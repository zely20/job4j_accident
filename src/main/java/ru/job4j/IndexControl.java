package ru.job4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<User> users = List.of(new User("Sasha","Zel"), new User("Olga", "Ver"));
        model.addAttribute("users", users);
        return "index";
    }
}
