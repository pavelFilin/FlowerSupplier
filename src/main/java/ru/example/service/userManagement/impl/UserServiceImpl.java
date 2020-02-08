package ru.filin.HavachMVC.service.userManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.HavachMVC.model.userManagement.RoleConstant;
import ru.filin.HavachMVC.model.userManagement.entities.Role;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;
import ru.filin.HavachMVC.model.userManagement.repositories.UserRepository;
import ru.filin.HavachMVC.service.mail.MailSender;
import ru.filin.HavachMVC.service.userManagement.UserContactsService;
import ru.filin.HavachMVC.service.userManagement.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserContactsService userContactsService;

    private PasswordEncoder passwordEncoder;

    private MailSender mailSender;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserContactsService userContactsService, PasswordEncoder passwordEncoder, MailSender mailSender) {
        this.userRepository = userRepository;
        this.userContactsService = userContactsService;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public void update(User obj) {
        userRepository.update(obj);
    }

    @Override
    public Long save(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User byEmail = getByEmail(email);
        return byEmail;
    }

    @Override
    public void updateRoles(long userId, Map<String, String> form) {
        User user = userRepository.getById(userId);

        Set<String> roles = Arrays.stream(RoleConstant.values())
                .map(RoleConstant::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(new Role(RoleConstant.valueOf(key).getCode(), key));
            }
        }

        userRepository.update(user);
    }

    @Override
    public void updateUserInfo(User user, @AuthenticationPrincipal User userFromDb, @RequestParam(required = false) String phone, @RequestParam(required = false) String address) {
        if (!StringUtils.isEmpty(user.getFirstName())) {
            userFromDb.setFirstName(user.getFirstName());
        }

        if (!StringUtils.isEmpty(user.getLastName())) {
            userFromDb.setLastName(user.getLastName());
        }

        if (!StringUtils.isEmpty(user.getPassword())) {
            userFromDb.setPassword(user.getPassword());
        }

        if (!StringUtils.isEmpty(phone) || !StringUtils.isEmpty(address)) {
            UserContacts userContacts = userContactsService.getByUserId(userFromDb.getId());
            if (userContacts != null) {
                userContacts.setAddress(address);
                userContacts.setPhone(phone);
                userContactsService.update(userContacts);
            } else {
                userContacts = new UserContacts(userFromDb.getId(), phone, address);
                userContactsService.save(userContacts);
            }
        }

        update(userFromDb);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.getRoles().add(new Role(RoleConstant.USER.getCode(), RoleConstant.USER.name()));
        user.setActive(false);

        user.setCode(UUID.randomUUID().toString());

        String message = String.format(
                "Hello, %s! \n" +
                        "Click to activate your account:  http://localhost:8080/activate/%s",
                user.getFirstName(),
                user.getCode()
        );

        mailSender.send(user.getEmail(), "Activation code", message);

        save(user);
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.getByCode(code);
        if (user == null) {
            return false;
        }

        user.setCode(null);
        user.setActive(true);
        userRepository.update(user);
        return true;
    }
}
