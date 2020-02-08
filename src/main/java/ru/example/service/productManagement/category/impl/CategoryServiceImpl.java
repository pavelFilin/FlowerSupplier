package ru.filin.HavachMVC.service.productManagement.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.repositories.category.CategoryRepository;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByTitle(String title) {
        return categoryRepository.getByTitle(title);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        categoryRepository.delete(id);
    }

    @Override
    public void update(Category obj) {
        categoryRepository.update(obj);
    }

    @Override
    public Long save(Category obj) {
        return categoryRepository.save(obj);
    }

    @Override
    public void addCategory(String title, String parentIdStr) {
        long parentId = -1;
        if (!StringUtils.isEmpty(parentIdStr)) {
            parentId = parseId(parentIdStr);
        }
        Category category = new Category(title, parentId);
        categoryRepository.save(category);
    }

    @Override
    public List<Long> getChildIds(long id) {
        return categoryRepository.findByParentId(id);
    }

    private long parseId(String id) {
        StringBuilder idString = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            if (Character.isDigit(id.charAt(i))) {
                idString.append(id.charAt(i));
            } else {
                break;
            }
        }
        return Long.parseLong(idString.toString());
    }
}
