package ru.filin.HavachMVC.model.productManagement.repositories.category;

import org.springframework.jdbc.core.RowMapper;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.service.BaseService;

import java.sql.ResultSet;
import java.util.List;

public interface CategoryRepository extends BaseService<Category> {
    RowMapper<Category> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Category(resultSet.getLong("id"), resultSet.getString("title"), resultSet.getLong("parent_id"));
    };

    Category getByTitle(String title);

    List<Long> findByParentId(long id);
}
