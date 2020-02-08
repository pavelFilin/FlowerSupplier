package ru.filin.HavachMVC.model.productManagement.repositories.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.productManagement.repositories.product.ProductRepository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, ROW_MAPPER);
    }

    @Override
    public Product getById(long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(query, id);

    }

    @Override
    public void update(Product obj) {
        String query = "UPDATE products SET name = ?, price = ?, description = ?, details_id = ?, stock = ?, active = ?,  photo = ?, category_id = ? WHERE id = ?";
        jdbcTemplate.update(
                query,
                obj.getName(),
                obj.getPrice(),
                obj.getDescription(),
                obj.getDetailsId(),
                obj.getStock(),
                obj.isActive(),
                obj.getPhoto(),
                obj.getCategoryId(),
                obj.getId()
        );
    }

    @Override
    public Long save(Product obj) {

        String getContactsId = "SELECT nextval(pg_get_serial_sequence('products', 'id'))";

        long contactsId = jdbcTemplate.query(getContactsId, rs -> {
            rs.next();
            return rs.getLong("nextval");
        });

        String saveContacts = "INSERT INTO " +
                "products (id, description, price, details_id, stock, photo, name, category_id, active) " +
                "VALUES (?,?,?,?,?,?,?,?,?);";

        jdbcTemplate.update(
                saveContacts,
                contactsId,
                obj.getDescription(),
                obj.getPrice(),
                obj.getDetailsId(),
                obj.getStock(),
                obj.getPhoto(),
                obj.getName(),
                obj.getCategoryId(),
                obj.isActive()
        );
        return contactsId;
    }

    @Override
    public List<Product> findByCategoryId(long category_id) {
        String findProductByCategoryId = "SELECT * FROM products WHERE category_id = ?";
        return jdbcTemplate.query(findProductByCategoryId, new Object[]{category_id}, ROW_MAPPER);
    }

    @Override
    public List<Product> getByNameAndActive(String name, String active) {

        if (StringUtils.isEmpty(active) || "none".equals(active)) {
            String findProductByCategoryId = "SELECT * FROM products WHERE name like ?";
            return jdbcTemplate.query(findProductByCategoryId, new Object[]{"%" + name + "%"}, ROW_MAPPER);
        } else {
            boolean a = false;
            if ("active".equals(active)) {
                a = true;
            }
            String findProductByCategoryId = "SELECT * FROM products WHERE name like ? and active = ?";
            return jdbcTemplate.query(findProductByCategoryId, new Object[]{"%" + name + "%", a}, ROW_MAPPER);
        }
    }

    @Override
    public void updateStock(long id, int stock) {
        String query = "UPDATE products SET stock = ? WHERE id = ?";
        jdbcTemplate.update(query, stock, id);
    }
}
