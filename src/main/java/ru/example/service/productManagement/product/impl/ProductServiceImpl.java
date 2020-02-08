package ru.filin.HavachMVC.service.productManagement.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.productManagement.repositories.category.CategoryRepository;
import ru.filin.HavachMVC.model.productManagement.repositories.product.ProductRepository;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;
import ru.filin.HavachMVC.utils.FileHelper;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${upload.path}")
    private String uploadPath;

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(long id) {
        return productRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public void update(Product obj) {
        productRepository.update(obj);
    }

    @Override
    public Long save(Product obj) {
        return productRepository.save(obj);
    }

    @Override
    public List<Product> findByCategoryId(long category_id) {
        return productRepository.findByCategoryId(category_id);
    }

    @Override
    public long add(Product product, MultipartFile file, String category) throws IOException {
        String path = FileHelper.loadFile(file, uploadPath);
        Category categoryFromDB = categoryRepository.getByTitle(category);
        product.setCategoryId(categoryFromDB.getId());
        product.setPhoto(path);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getByNameAndActive(String name, String active) {
        return productRepository.getByNameAndActive(name, active);
    }

    @Override
    public void edit(Product product, MultipartFile file, String category) throws IOException {
        if (!file.isEmpty()) {
            String path = FileHelper.loadFile(file, uploadPath);
            product.setPhoto(path);
        }
        Category categoryFromDB = categoryRepository.getByTitle(category);
        product.setCategoryId(categoryFromDB.getId());
        productRepository.update(product);
    }
}
