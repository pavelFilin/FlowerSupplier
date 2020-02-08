package ru.filin.HavachMVC.controller.userManagement.profile.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.controller.userManagement.profile.ProfileController;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;
import ru.filin.HavachMVC.service.userManagement.UserContactsService;
import ru.filin.HavachMVC.service.userManagement.UserService;

import java.util.List;

@Controller
@RequestMapping("/user/profile")
public class ProfileControllerImpl implements ProfileController {

    private UserService userService;

    private UserContactsService userContactsService;

    @Autowired
    public ProfileControllerImpl(UserService userService, UserContactsService userContactsService) {
        this.userService = userService;
        this.userContactsService = userContactsService;
    }

    @Override
    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal User user, Model model) {
        UserContacts userContacts = userContactsService.getByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userContacts", userContacts);
        return "userPages/profile";
    }

    @Override
    @PostMapping
    public String updateUser(
            User user,
            @AuthenticationPrincipal User userFromDb,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            Model model
    ) {
        userService.updateUserInfo(user, userFromDb, phone, address);

        model.addAttribute("user", userFromDb);
        return "redirect:/user/profile";
    }

    @Override
    @GetMapping("userlist")
    public String getUserList(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "userPages/usersrolemanagment";
    }


}
