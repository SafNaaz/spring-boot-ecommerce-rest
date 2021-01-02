package com.safnas.springbootecommerce.service;

import com.safnas.springbootecommerce.dto.Purchase;
import com.safnas.springbootecommerce.dto.PurchaseResponse;
import com.safnas.springbootecommerce.entity.Customer;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    Customer getOrder(String email);
}
