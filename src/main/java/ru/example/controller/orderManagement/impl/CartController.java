package ru.filin.HavachMVC.controller.orderManagement.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.filin.HavachMVC.controller.DTO.CartConvector;
import ru.filin.HavachMVC.controller.DTO.CartDTO;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;
import ru.filin.HavachMVC.service.orderManagement.impl.CartServiceImpl;
import ru.filin.HavachMVC.service.productManagement.product.ProductService;
import ru.filin.HavachMVC.service.userManagement.UserContactsService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartServiceImpl cartService;

    private UserContactsService userContactsService;

    private ProductService productService;

    @Autowired
    public CartController(CartServiceImpl cartService, UserContactsService userContactsService, ProductService productService) {
        this.cartService = cartService;
        this.userContactsService = userContactsService;
        this.productService = productService;
    }

    @GetMapping
    public String getCartPage(@AuthenticationPrincipal User user, Model model) {
        List<CartItem> cart = cartService.findCartsByUser(user.getId());
        List<CartDTO> cartDTOS = cart.stream().map(c -> CartConvector.toCartDTO(c)).collect(Collectors.toList());
        UserContacts userContacts = userContactsService.getByUserId(user.getId());

        int totalQuantity = cartDTOS.stream().mapToInt(CartDTO::getQuantity).sum();
        int totalPrice = cartDTOS.stream()
                .mapToInt(c -> c.getProduct().getPrice() * c.getQuantity())
                .sum();

        model.addAttribute("cart", cartDTOS);
        model.addAttribute("userContacts", userContacts);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalPrice", totalPrice);

        return "orderPages/cart";
    }

    @PostMapping("/add/")
    public String addNewItem(@AuthenticationPrincipal User user, @RequestParam long productId, @RequestParam int quantity) {
        cartService.saveCartItem(user.getId(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String removeItem(@RequestParam long cartId) {
        cartService.deleteCartItem(cartId);
        return new Gson().toJson("success");
    }

    @PostMapping(value = "change/orderstatus")
    @ResponseBody
    public String changeQuantity(@AuthenticationPrincipal User user, @RequestParam("productId") long productId, @RequestParam("quantity") int quantity) {
        Product product = productService.getById(productId);
        if (product.getStock() < quantity) {
            return new Gson().toJson(product.getName() +" is not available!");
        }

        cartService.changeQuantity(user.getId(), productId, quantity);
        return new Gson().toJson("success");
    }

}
