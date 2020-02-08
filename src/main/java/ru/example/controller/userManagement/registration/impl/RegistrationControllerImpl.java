package ru.filin.HavachMVC.controller.userManagement.registration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.controller.userManagement.registration.RegistrationController;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.userManagement.UserService;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationControllerImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        return "userPages/registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, @RequestParam String passwordConfirmation, Model model) {
        User userFromDB = userService.getByEmail(user.getEmail());

        if (userFromDB != null) {
            model.addAttribute("message", "User is exit");
            model.addAttribute("user", user);
            return "userPages/registration";
        }

        if (!user.getPassword().equals(passwordConfirmation)) {
            model.addAttribute("message", "password and confirm password not matched");
            model.addAttribute("user", user);
            return "userPages/registration";
        }

        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User has been activated");
        } else {
            model.addAttribute("message", "Code of activation is not valid");
        }
        return "userPages/login";
    }
}
