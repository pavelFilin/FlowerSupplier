package ru.filin.HavachMVC.controller.productManagement.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

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
    public String catalog(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "shopPages/catalog";
    }

    @GetMapping("/productlist/{categoryId}")
    public  String getProductsByCategory(@PathVariable long categoryId, Model model) {
        List<Product> products = productService.findByCategoryId(categoryId);
        products = products.stream().filter(p -> p.isActive()).collect(Collectors.toList());
        model.addAttribute("products", products);
        return "shopPages/productlist";
    }
}
