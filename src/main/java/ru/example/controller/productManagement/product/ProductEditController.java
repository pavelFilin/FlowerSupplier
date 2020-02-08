package ru.filin.HavachMVC.controller.productManagement.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product/management")
public class ProductEditController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ProductEditController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String getProductAddPage(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "productPages/addproduct";
    }

    @PostMapping
    public String addProduct(Product product, @RequestParam("file") MultipartFile file, @RequestParam String category) {
        long productId = 0;
        try {
            productId = productService.add(product, file, category);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/product/" + productId;
    }

    @GetMapping("edit/{productId}")
    public String editProductPage(@PathVariable long productId, Model model) {
        Product product = productService.getById(productId);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "productPages/editproduct";
    }

    @PostMapping("edit")
    public String editProduct(Product product, @RequestParam("file") MultipartFile file, @RequestParam String category) {
        try {
            productService.edit(product, file, category);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/product/" + product.getId();
    }
}
