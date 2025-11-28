package com.example.carservice.repository;

import com.example.carservice.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    // Наследование от MongoRepository.
    // Spring сам сгенерирует методы: findAll, save, findById, deleteById.
}