package ru.filin.HavachMVC.model.userManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;
import ru.filin.HavachMVC.model.userManagement.repositories.UserContactsRepository;

import java.util.List;

@Repository
public class UserContactsRepositoryImpl implements UserContactsRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserContactsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserContacts> getAll() {
        String getAllContacts = "SELECT * FROM user_contacts";
        return jdbcTemplate.query(getAllContacts, ROW_MAPPER);
    }

    @Override
    public UserContacts getById(long id) {
        String getAllContacts = "SELECT * FROM user_contacts WHERE id = ?";
        return jdbcTemplate.queryForObject(getAllContacts, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void delete(long id) {
        String getAllContacts = "SELECT FROM user_contacts WHERE id = ?";
        jdbcTemplate.update(getAllContacts, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void update(UserContacts obj) {
        String getAllContacts = "UPDATE user_contacts SET user_id = ?, phone = ? , address = ? WHERE id = ?";
        jdbcTemplate.update(
                getAllContacts,
                new Object[]{obj.getUserId(), obj.getPhone(), obj.getAddress(), obj.getId()}
        );
    }

    @Override
    public Long save(UserContacts obj) {
        String getProductId = "SELECT nextval(pg_get_serial_sequence('user_contacts', 'id'))";

        long userId = jdbcTemplate.query(getProductId, rs -> {
            rs.next();
            return rs.getLong("nextval");
        });

        String getAllContacts = "INSERT INTO user_contacts (id, user_id, phone, address) VALUES (?,?,?,?)";
        jdbcTemplate.update(
                getAllContacts,
                new Object[]{userId, obj.getUserId(), obj.getPhone(), obj.getAddress()}
        );

        return userId;
    }

    @Override
    public UserContacts getByUserId(long id) {
        String getAllContacts = "SELECT * FROM user_contacts WHERE user_id = ?";
        return jdbcTemplate.queryForObject(getAllContacts, new Object[]{id}, ROW_MAPPER);
    }
}
