package ru.filin.HavachMVC.controller.userManagement.profile;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.filin.HavachMVC.model.userManagement.entities.User;

public interface ProfileController {
    String getProfilePage(@AuthenticationPrincipal User user, Model model);

    @PostMapping
    String updateUser(User user, @AuthenticationPrincipal User userFromDb,  String phone,
                      String address, Model model);

    @GetMapping("userlist")
    String getUserList(Model model);
}
