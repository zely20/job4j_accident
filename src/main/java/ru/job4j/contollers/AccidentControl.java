package ru.job4j.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.Accident;
import ru.job4j.model.Rule;
import ru.job4j.repository.AccidentHibernate;
import ru.job4j.repository.AccidentJdbcTemplate;
import ru.job4j.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentHibernate accidents;

    public AccidentControl(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.getAllTypes());
        model.addAttribute("rules", accidents.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        for(String id : ids) {
            Rule rule = new Rule();
            rule.setId(Integer.parseInt(id));
            accident.getRules().add(rule);
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String replace(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        model.addAttribute("types", accidents.getAllTypes());
        model.addAttribute("rules", accidents.getAllRules());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        for(String id : ids) {
            Rule rule = new Rule();
            rule.setId(Integer.parseInt(id));
            accident.getRules().add(rule);
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        accidents.delete(id);
        return "redirect:/";
    }
}
