package ru.filin.HavachMVC.controller.productManagement.category.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.service.productManagement.category.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
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
        Category category = categoryService.getById(id);
        List<Long> childIds = categoryService.getChildIds(id);
        if (childIds.size() > 0) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "This category id[" + id + "] has children categories " + childIds.toString());
        } else {
            categoryService.delete(id);
        }

        return "redirect:/category";
    }


}
