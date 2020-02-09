package ru.example.controller.productManagement.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.model.productManagement.entities.Product;
import ru.example.service.productManagement.category.CategoryService;
import ru.example.service.productManagement.product.ProductService;
import ru.example.model.productManagement.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("catalog")
public class CatalogController {

    private CategoryService categoryService;

    private ProductService productService;

    @Autowired
    public CatalogController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String catalog(@RequestParam(required = false) Integer catalogId, Model model) {
        List<Product> products;
        if (catalogId != null) {
            products = productService.findByCategoryId(categoryService.getById(catalogId).getId());
            products = products.stream().filter(Product::isActive).collect(Collectors.toList());
        } else {
            products = productService.getAll();
            products = products.stream().filter(Product::isActive).collect(Collectors.toList());
        }

        model.addAttribute("products", products);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "shopPages/catalog";
    }

    @GetMapping("/productlist/{categoryId}")
    public String getProductsByCategory(@PathVariable long categoryId, Model model) {
        List<Product> products = productService.findByCategoryId(categoryId);
        products = products.stream().filter(Product::isActive).collect(Collectors.toList());
        model.addAttribute("products", products);
        return "shopPages/productlist";
    }
}
