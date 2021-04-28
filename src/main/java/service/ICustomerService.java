package service;

import model.Customer;
import model.Province;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();
    Optional<Customer> findById(Long id);
    void save(Customer customer);
    void delete(Long id);
    Iterable<Customer> findAllByProvince(Province province);
}
