package ru.example.service.productManagement.category;

import ru.example.model.productManagement.entities.Category;
import ru.example.service.BaseService;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    Category findByTitle(String title);

    void addCategory(String title, String id);

    List<Long> getChildIds(long id);
}
