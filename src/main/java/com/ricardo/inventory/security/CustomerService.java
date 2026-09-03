package com.ricardo.inventory.service;

import com.ricardo.inventory.entity.Customer;
import com.ricardo.inventory.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer update(Long id, Customer updatedData) {
        Customer existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        existing.setCompany(updatedData.getCompany());
        existing.setContact(updatedData.getContact());
        existing.setEmail(updatedData.getEmail());
        existing.setPhone(updatedData.getPhone());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}