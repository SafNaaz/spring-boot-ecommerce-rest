package com.safnas.springbootecommerce.dto;

import com.safnas.springbootecommerce.entity.Address;
import com.safnas.springbootecommerce.entity.Customer;
import com.safnas.springbootecommerce.entity.Order;
import com.safnas.springbootecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
