package ru.job4j.contollers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.entity.Accident;
import ru.job4j.service.AccidentService;

import java.util.List;

@Controller
public class IndexControl {

    private final AccidentService accidentService;

    public IndexControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Accident> accidents = accidentService.findAllFetchAccident();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
