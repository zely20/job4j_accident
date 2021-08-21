package ru.job4j.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.entity.Accident;
import ru.job4j.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAllTypes());
        model.addAttribute("rules", accidentService.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidentService.saveAccident(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String replace(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("accident", accidentService.getFetchAccidentById(id));
        model.addAttribute("types", accidentService.getAllTypes());
        model.addAttribute("rules", accidentService.getAllRules());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
       accidentService.updateAccident(accident,ids);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        accidentService.deleteAccident(id);
        return "redirect:/";
    }
}
