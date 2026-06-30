package com.floydrise.tacocloud.tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;
import com.floydrise.tacocloud.tacos.attributes.User;
import com.floydrise.tacocloud.tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {
    private OrderRepository orderRepo;
    private OrderProps orderProps;

    public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
        this.orderRepo = orderRepository;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute("tacoOrder") TacoOrder tacoOrder, @AuthenticationPrincipal User user) {
        tacoOrder.setDeliveryName(user.getFullname());
        tacoOrder.setDeliveryCity(user.getCity());
        tacoOrder.setDeliveryState(user.getState());
        tacoOrder.setDeliveryStreet(user.getStreet());
        tacoOrder.setDeliveryZip(user.getZip());
        return "orderForm";
    }

    @GetMapping("/order_list")
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        tacoOrder.setUser(user);

        System.out.println(user);
        orderRepo.save(tacoOrder);
        log.info("Processing taco order: {}", tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/orders/order_list";
    }
}
