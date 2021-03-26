package com.safnas.springbootecommerce.service;

import com.safnas.springbootecommerce.dao.CustomerRepository;
import com.safnas.springbootecommerce.dto.Purchase;
import com.safnas.springbootecommerce.dto.PurchaseResponse;
import com.safnas.springbootecommerce.entity.Customer;
import com.safnas.springbootecommerce.entity.Order;
import com.safnas.springbootecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //Retrieve order info from dto
        Order order = purchase.getOrder();

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

        //populate order with addresses
        order.setShippingAddress(purchase.getShippingAddress());
        order.setBillingAddress(purchase.getBillingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if(customerFromDB != null){
            customer = customerFromDB;
        }

        customer.add(order);

        // save to database
        customerRepository.save(customer);

        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public Customer getOrder(String email) {
        return customerRepository.findByEmail(email);
    }

    private String generateOrderTrackingNumber() {

        //generate a random UUID
        return UUID.randomUUID().toString();

    }
}
