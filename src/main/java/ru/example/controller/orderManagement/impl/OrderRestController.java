package ru.filin.HavachMVC.controller.orderManagement.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.filin.HavachMVC.service.orderManagement.impl.OrderServiceImpl;

@Controller
@RequestMapping("/order")
public class OrderRestController {

    private OrderServiceImpl orderService;

    public OrderRestController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "change/orderstatus")
    @ResponseBody
    public String changeOrderStatus(@RequestParam(value = "id") long id, @RequestParam("orderStatus") String orderStatus) {
        orderService.changeOrderStatus(id, orderStatus);
        return "success";
    }

}


