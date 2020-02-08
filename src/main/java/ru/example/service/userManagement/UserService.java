package ru.filin.HavachMVC.service.userManagement;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.BaseService;

import java.util.Map;

public interface UserService extends BaseService<User>, UserDetailsService {
    User getByEmail(String email);

    void updateRoles(long userId, Map<String, String> form);

    void updateUserInfo(User user, @AuthenticationPrincipal User userFromDb, @RequestParam(required = false) String phone, @RequestParam(required = false) String address);

    void addUser(User user);

    boolean activateUser(String code);
}
