package service;

import model.Province;

import java.util.Optional;

public interface IProvinceService {
    Iterable<Province> findAll();
    Optional<Province> findById(Long id);
    void save(Province province);
    void delete(Long id);
}
