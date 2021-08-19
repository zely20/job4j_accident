package ru.job4j.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.Accident;
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
        List<Accident> accidents = accidentService.findAllFetchAccident();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
