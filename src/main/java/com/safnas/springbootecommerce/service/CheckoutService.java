package com.safnas.springbootecommerce.service;

import com.safnas.springbootecommerce.dto.Purchase;
import com.safnas.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
