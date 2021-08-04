package ru.job4j.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.model.Accident;
import ru.job4j.repository.AccidentJdbcTemplate;

import java.util.List;

@Controller
public class IndexControl {

    private final AccidentJdbcTemplate accidentsMem;

    public IndexControl(AccidentJdbcTemplate accidents) {
        this.accidentsMem = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = accidentsMem.getAll();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
