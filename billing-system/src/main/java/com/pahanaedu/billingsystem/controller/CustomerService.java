package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.exception.ResourceNotFoundException;
import com.pahanaedu.billingsystem.model.Customer;
import com.pahanaedu.billingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // ✅ New: edit/update customer
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        customerRepository.delete(customer);
    }
}
