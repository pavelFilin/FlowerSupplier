package ru.example.model.userManagement.repositories;

import ru.example.model.userManagement.entities.User;
import ru.example.model.BaseRepository;

public interface UserRepository extends BaseRepository<User> {
    User getByEmail(String email);

    User getByCode(String code);
}
