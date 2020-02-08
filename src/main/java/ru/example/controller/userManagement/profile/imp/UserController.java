package ru.filin.HavachMVC.controller.userManagement.profile.imp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.model.userManagement.RoleConstant;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.userManagement.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String userEditPage(@PathVariable long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("rolesConst", Arrays.asList(RoleConstant.values()));
        List roles = Arrays.stream(RoleConstant.values()).filter(roleConstant -> checkRoleInUser(roleConstant, user)).collect(Collectors.toList());
        model.addAttribute("userRoles", roles);
        return "userPages/edituser";
    }

    private boolean checkRoleInUser(RoleConstant roleConstant, User user) {
        return user.getRoles().stream().filter(userRole -> userRole.getName().equals(roleConstant.name())).count() > 0;
    }

    @PostMapping("/user")
    public String userSave(
            @RequestParam long id,
            @RequestParam Map<String, String> form
    ) {
        userService.updateRoles(id, form);
        return "redirect:/user/profile/userlist";
    }
}
