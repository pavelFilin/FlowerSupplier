package ru.filin.HavachMVC.model.userManagement.repositories;

import ru.filin.HavachMVC.model.BaseRepository;
import ru.filin.HavachMVC.model.userManagement.entities.User;

public interface UserRepository extends BaseRepository<User> {
    User getByEmail(String email);

    User getByCode(String code);
}
