package carshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String root() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/add_car")
    public String addCar() {
        return "add_car";
    }

    @RequestMapping(value = "/add_car-success")
    public String addCarSuccessfull(Model model) {
        model.addAttribute("success", true);
        return "add_car";
    }
}
