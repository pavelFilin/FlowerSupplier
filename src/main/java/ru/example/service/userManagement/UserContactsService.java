package ru.filin.HavachMVC.service.userManagement;

import ru.filin.HavachMVC.model.BaseRepository;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;

public interface UserContactsService extends BaseRepository<UserContacts> {
    UserContacts getByUserId(long id);
}
