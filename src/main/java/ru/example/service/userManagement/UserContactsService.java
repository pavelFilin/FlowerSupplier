package ru.example.service.userManagement;

import ru.example.model.userManagement.entities.UserContacts;
import ru.example.model.BaseRepository;

public interface UserContactsService extends BaseRepository<UserContacts> {
    UserContacts getByUserId(long id);
}
