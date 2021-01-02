package com.safnas.springbootecommerce.controller;

import com.safnas.springbootecommerce.dto.Purchase;
import com.safnas.springbootecommerce.dto.PurchaseResponse;
import com.safnas.springbootecommerce.entity.Customer;
import com.safnas.springbootecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }

    @GetMapping("/orders/{email}")
    @ResponseBody
    public Customer getOrderInfo(@PathVariable String email){

        Customer customer = checkoutService.getOrder(email);

        return customer;
    }
}
