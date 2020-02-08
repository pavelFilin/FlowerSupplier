package ru.example.model.userManagement.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.example.model.userManagement.entities.UserContacts;
import ru.example.model.BaseRepository;

import java.sql.ResultSet;

public interface UserContactsRepository extends BaseRepository<UserContacts> {
    RowMapper<UserContacts> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new UserContacts(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getString("phone"),
                resultSet.getString("address")
        );
    };

    UserContacts getByUserId(long id);
}
