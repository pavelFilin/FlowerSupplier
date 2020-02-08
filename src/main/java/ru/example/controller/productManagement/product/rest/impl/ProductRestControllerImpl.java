package ru.filin.HavachMVC.controller.productManagement.product.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.filin.HavachMVC.controller.productManagement.product.rest.ProductRestController;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product/rest")
public class ProductRestControllerImpl implements ProductRestController {

    private ProductService productService;

    @Autowired
    public ProductRestControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Override
    @GetMapping({"id"})
    public Product getById(@PathVariable long id) {
        return productService.getById(id);
    }

    @Override
    @DeleteMapping({"id"})
    public void delete(long id) {
        productService.delete(id);
    }

    @PutMapping("id")
    public void update(Product obj) {
        productService.update(obj);
    }

    @PostMapping
    public Long save(Product obj) {
        return productService.save(obj);
    }


}
