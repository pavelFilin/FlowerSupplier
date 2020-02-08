package ru.filin.HavachMVC.model.productManagement.repositories.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.repositories.category.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getAll() {
        String getAllCategory = "SELECT * FROM category";
        return jdbcTemplate.query(getAllCategory, ROW_MAPPER);
    }

    @Override
    public Category getById(long id) {
        String getCategoryById = "SELECT * FROM category WHERE id = ?";
        return jdbcTemplate.queryForObject(getCategoryById, new Object[]{id}, ROW_MAPPER);
    }

    @Override
    public Category getByTitle(String title) {
        String getCategoryById = "SELECT * FROM category WHERE title = ?";
        return jdbcTemplate.queryForObject(getCategoryById, new Object[]{title}, ROW_MAPPER);
    }

    @Override
    public List<Long> findByParentId(long id) {
        String findCategoryByParentId = "SELECT * FROM category WHERE parent_id = ?";
        return jdbcTemplate.query(findCategoryByParentId, new Object[]{id}, ROW_MAPPER)
                .stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        String getCategoryById = "DELETE FROM category WHERE id = ?";
        jdbcTemplate.update(getCategoryById, new Object[]{id});
    }

    @Override
    public void update(Category obj) {
        String updateCategory = "UPDATE category SET title = ?, parent_id = ? WHERE id = ?";
        jdbcTemplate.update(updateCategory, obj.getTitle(), obj.getParentId(), obj.getId());
    }

    @Override
    public Long save(Category obj) {
        String saveCategory = "INSERT INTO category (title, parent_id) VALUES (?, ?)";
        if (obj.getParentId() > 0) {
            jdbcTemplate.update(saveCategory, obj.getTitle(), obj.getParentId());
        } else {

            jdbcTemplate.update(saveCategory, obj.getTitle(), null);
        }
        return 0L;
    }
}
