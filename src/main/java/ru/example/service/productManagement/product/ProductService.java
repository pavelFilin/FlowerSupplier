package ru.example.service.productManagement.product;


import org.springframework.web.multipart.MultipartFile;
import ru.example.model.productManagement.entities.Product;
import ru.example.model.BaseRepository;

import java.io.IOException;
import java.util.List;

public interface ProductService extends BaseRepository<Product> {
    List<Product> findByCategoryId(long category_id);

    long add(Product product, MultipartFile file, String category) throws IOException;

    List<Product> getByNameAndActive(String name, String active);

    void edit(Product product, MultipartFile file, String category) throws IOException;
}
