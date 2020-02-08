package ru.filin.HavachMVC.service.userManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;
import ru.filin.HavachMVC.model.userManagement.repositories.UserContactsRepository;
import ru.filin.HavachMVC.service.userManagement.UserContactsService;

import java.util.List;

@Service
public class UserContactsServiceImpl implements UserContactsService {

    private UserContactsRepository userContactsRepository;

    @Autowired
    public UserContactsServiceImpl(UserContactsRepository userContactsRepository) {
        this.userContactsRepository = userContactsRepository;
    }


    @Override
    public List<UserContacts> getAll() {
        return userContactsRepository.getAll();
    }

    @Override
    public UserContacts getById(long id) {
        return userContactsRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        userContactsRepository.delete(id);
    }

    @Override
    public void update(UserContacts obj) {
        userContactsRepository.update(obj);
    }

    @Override
    public Long save(UserContacts obj) {
        return userContactsRepository.save(obj);
    }

    @Override
    public UserContacts getByUserId(long id) {
        UserContacts byUserId = null;
        try {
            byUserId = userContactsRepository.getByUserId(id);
        } catch (EmptyResultDataAccessException e) {

        }
        return byUserId;
    }
}
