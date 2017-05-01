package carshop.controllers;

import carshop.entities.Role;
import carshop.entities.User;
import carshop.repositories.RoleRepository;
import carshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login-error")
    public String login(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register",  method = RequestMethod.POST)
    public String register(WebRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null) {
            model.addAttribute("failure", true);
            return "register";
        }

        Role role = roleRepository.findByRole("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setRole("ROLE_USER");
            roleRepository.save(role);
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        userService.saveUser(user);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, password));

        return "redirect:/home";
    }
}
