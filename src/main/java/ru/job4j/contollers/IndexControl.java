package ru.job4j.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;

import java.util.List;

@Controller
public class IndexControl {

    private final AccidentMem accidentsMem;

    public IndexControl(AccidentMem accidents) {
        this.accidentsMem = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = accidentsMem.findAll();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
