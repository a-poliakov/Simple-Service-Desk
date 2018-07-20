package ru.apolyakov.controller;

import ru.apolyakov.model.Priority;
import ru.apolyakov.model.ServiceCall;
import ru.apolyakov.service.EmployeeService;
import ru.apolyakov.service.ServiceCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ServiceCallController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ServiceCallService serviceCallService;

    @GetMapping("/servicecall")
    public String index(Model model) {
        model.addAttribute("servicecalls", serviceCallService.findAll());
        return "servicecallslist";
    }

    @GetMapping("/servicecall/create")
    public String create(Model model) {
        model.addAttribute("servicecall", new ServiceCall());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("priorities", Priority.values());
        return "servicecallform";
    }

    @GetMapping("/servicecall/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("servicecall", serviceCallService.findOne(id));
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("priorities", Priority.values());
        return "servicecallform";
    }

    @PostMapping("/servicecall/save")
    public String save(@Valid ServiceCall serviceCall, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "servicecallform";
        }

        serviceCallService.save(serviceCall);
        redirect.addFlashAttribute("success", "Saved servicecall successfully!");
        return "redirect:/servicecall";
    }

    @GetMapping("/servicecall/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        serviceCallService.delete(id);
        redirect.addFlashAttribute("success", "Deleted servicecall successfully!");
        return "redirect:/servicecall";
    }

    @GetMapping("/servicecall/search")
    public String search(@RequestParam("s") String s, Model model) {
        if (s.equals("")) {
            return "redirect:/servicecall";
        }
        model.addAttribute("servicecalls", serviceCallService.search(s));
        return "servicecallslist";
    }
}
