package ru.example.controller.productManagement.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.example.model.productManagement.entities.Category;
import ru.example.model.productManagement.entities.Product;
import ru.example.service.productManagement.category.CategoryService;
import ru.example.service.productManagement.product.ProductService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAll();

        model.addAttribute("categories", categories);
        return "userPages/categorymanagement";
    }

    @PostMapping
    public String addNewCategory(@RequestParam String title, @RequestParam(required = false) String parentId) {
        categoryService.addCategory(title, parentId);
        return "redirect:/category";
    }

    @PostMapping("/{id}")
    public String deleteCategory(@PathVariable long id, final RedirectAttributes redirectAttributes) {
        List<Long> childIds = categoryService.getChildIds(id);
        List<Product> products = productService.findByCategoryId(id);

        if (childIds.size() > 0) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Категория id[" + id + "] имеет дочерние категории " + childIds.toString());
        } else {
            if (products.size() > 0) {
                redirectAttributes.addFlashAttribute(
                        "message",
                        "Категория id[" + id + "] имеет продукты " + products.stream().collect(Collectors.toMap(Product::getId, Product::getName)).toString());
            } else {
                categoryService.delete(id);
            }
        }

        return "redirect:/category";
    }


}
