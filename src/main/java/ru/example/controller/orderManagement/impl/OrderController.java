package ru.filin.HavachMVC.controller.orderManagement.impl;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.controller.DTO.Message;
import ru.filin.HavachMVC.controller.DTO.OrderDTO;
import ru.filin.HavachMVC.model.orderManagement.entities.Order;
import ru.filin.HavachMVC.model.userManagement.entities.User;
import ru.filin.HavachMVC.service.orderManagement.impl.OrderServiceImpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrders(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.getOrderByUser(user.getId());
        orders.sort(Comparator.comparing(Order::getTimeCreated).reversed());
        model.addAttribute("orders", orders);
        return "orderPages/userorderlist";
    }

    @GetMapping("list")
    public String getOrdersForAdmin(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.getOrders();
        orders.sort(Comparator.comparing(Order::getTimeCreated).reversed());
        model.addAttribute("orders", orders);
        model.addAttribute("orderStatus", Arrays.asList(OrderStatus.values()));
        return "orderPages/adminorderlist";
    }

    @GetMapping("{orderId}")
    public String getOrder(@AuthenticationPrincipal User user, @PathVariable long orderId, Model model) {
        OrderDTO orderDTO = orderService.getOrderByUserIdAndOrderId(user.getId(), orderId);

        model.addAttribute("order", orderDTO);
        model.addAttribute("nameOrderItems", orderDTO.getOrderItems().stream().map(oi -> oi.getProduct().getName()).collect(Collectors.joining(", ")));
        return "orderPages/userorder";
    }


    @PostMapping
    public String makeOrder(@AuthenticationPrincipal User user,
                            @RequestParam String phone,
                            @RequestParam String address,
                            @RequestParam String paymentType, Model model, final RedirectAttributes redirectAttributes) {
        long orderId = 0;
        Message message = new Message();
        if (!(StringUtils.isEmpty(phone) || StringUtils.isEmpty(address) || StringUtils.isEmpty(paymentType))) {
            orderId = orderService.makeOrder(user.getId(), phone, address, paymentType, message);
        } else {
            redirectAttributes.addFlashAttribute("message", "Bad creditails");
            return "redirect:/cart";
        }

        if (message.getString().length() > 0) {
            redirectAttributes.addFlashAttribute("message", message.getString());
            return "redirect:/cart";
        }

        return "redirect:/order/" + orderId;
    }
}
