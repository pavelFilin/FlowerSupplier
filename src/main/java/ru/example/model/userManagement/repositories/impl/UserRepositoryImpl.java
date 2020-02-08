package ru.filin.HavachMVC.model.userManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.filin.HavachMVC.model.userManagement.entities.Role;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.repositories.UserRepository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        String getAllUsers = "SELECT * " +
                "FROM usr u \n" +
                "INNER JOIN user_roles ur ON u.id=ur.user_id \n" +
                "INNER JOIN roles r on ur.role_id = r.id";

        Map<Long, User> users = new HashMap<>();

        jdbcTemplate.query(getAllUsers, (rs, rowNum) -> {

            do {
                User user = new User();
                long id = rs.getLong("id");
                if (users.containsKey(id)) {
                    User userAlreadyExist = users.get(id);
                    List<Role> roles = userAlreadyExist.getRoles();
                    roles.add(new Role(rs.getLong("role_id"), rs.getString("name")));
                } else {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                    user.setId(id);
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setActive(rs.getBoolean("active"));
                    user.setCode(rs.getString("code"));
                    users.put(user.getId(), user);
                }

            }
            while (rs.next());
            return null;
        });

        return new ArrayList<>(users.values());
    }

    @Override
    public User getById(long id) {
        String getUserById = "SELECT * " +
                "FROM usr u \n" +
                "INNER JOIN user_roles ur ON u.id=ur.user_id \n" +
                "INNER JOIN roles r on ur.role_id = r.id where u.id = ?";

        final User user = new User();
        jdbcTemplate.queryForObject(getUserById, new Object[]{id}, (rs, rowNum) -> {
            do {
                if (user.getId() != 0) {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                } else {
                    user.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
                    user.setId(rs.getLong("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setPassword(rs.getString("password"));
                    user.setActive(rs.getBoolean("active"));
                    user.setCode(rs.getString("code"));
                }
            }
            while (rs.next());
            return null;
        });
        return user;
    }

    @Override
    public void delete(long id) {
        String deleteUser;
        return;
    }

    @Override
    @Transactional
    public void update(User user) {
        String updateUser = "UPDATE usr SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "password = ?, " +
                "active = ?, " +
                "code = ?" +
                "WHERE email = ?";

        jdbcTemplate.update(
                updateUser,
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.isActive(),
                user.getCode(),
                user.getEmail()
        );

        String roleClear = "DELETE FROM user_roles WHERE user_id = ?";
        jdbcTemplate.update(roleClear, user.getId());

        String updateRole = "INSERT INTO user_roles " +
                "(user_id, role_id) " +
                "values (?, ?)";

        for (Role role : user.getRoles()) {
            jdbcTemplate.update(updateRole, user.getId(), role.getId());
        }
    }

    @Override
    @Transactional
    public Long save(User obj) {
        String getIdByUser = "SELECT nextval(pg_get_serial_sequence('usr', 'id'))";

        long userId = jdbcTemplate.query(getIdByUser, rs -> {
            rs.next();
            return rs.getLong("nextval");
        });

        String saveUser = "INSERT INTO " +
                "   usr (id, first_name, last_name, password, email, active, code) " +
                "VALUES (?,?,?,?,?,?,?); ";

        jdbcTemplate.update(
                saveUser,
                userId,
                obj.getFirstName(),
                obj.getLastName(),
                obj.getPassword(),
                obj.getEmail(),
                obj.isActive(),
                obj.getCode()
        );

        String saveRoles = "INSERT INTO " +
                "user_roles (user_id, role_id) " +
                "VALUES (" + userId + ", " + "?)";

        List<Role> roles = obj.getRoles();
        for (Role role : roles) {
            long code = role.getId();
            jdbcTemplate.update(saveRoles, new Object[]{code});
        }

        return userId;
    }

    @Override
    public User getByEmail(String email) {
        String getUserByEmail = "SELECT * " +
                "FROM usr u \n" +
                "INNER JOIN user_roles ur ON u.id=ur.user_id \n" +
                "INNER JOIN roles r on ur.role_id = r.id where u.email = ?";

        List<User> users = jdbcTemplate.query(getUserByEmail, new Object[]{email}, (rs, rowNum) -> {
            User u = new User();
            u.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
            u.setId(rs.getLong("id"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setFirstName(rs.getString("first_name"));
            u.setLastName(rs.getString("last_name"));
            u.setActive(rs.getBoolean("active"));
            u.setCode(rs.getString("code"));
            return u;
        });

        Optional<User> user = users.stream().reduce((user1, user2) -> {
            user1.getRoles().add(user2.getRoles().get(0));
            return user1;
        });

        return user.orElse(null);
    }

    @Override
    public User getByCode(String code) {
        String getUserByEmail = "SELECT * " +
                "FROM usr u \n" +
                "INNER JOIN user_roles ur ON u.id=ur.user_id \n" +
                "INNER JOIN roles r on ur.role_id = r.id where u.code = ?";

        List<User> users = jdbcTemplate.query(getUserByEmail, new Object[]{code}, (rs, rowNum) -> {
            User u = new User();
            u.getRoles().add(new Role(rs.getLong("role_id"), rs.getString("name")));
            u.setId(rs.getLong("id"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setFirstName(rs.getString("first_name"));
            u.setLastName(rs.getString("last_name"));
            u.setActive(rs.getBoolean("active"));
            u.setCode(rs.getString("code"));
            return u;
        });

        return users.stream().findAny().orElse(null);
    }
}
