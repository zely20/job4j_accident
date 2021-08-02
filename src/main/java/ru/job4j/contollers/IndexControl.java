package ru.job4j.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentMem;

import java.util.List;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        Accident accident1 = new Accident(1, "dtp", "fdsfsf", "Minsk");
        Accident accident2 = new Accident(2, "dtp2", "ffdsfsd", "Lagoysk");
        AccidentMem accidentMem = new AccidentMem();
        accidentMem.add(accident1);
        accidentMem.add(accident2);
        List<Accident> accidents = accidentMem.findAll();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
