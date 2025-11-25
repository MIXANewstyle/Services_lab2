package com.example.carservice.controller;

import com.example.carservice.model.Car;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController // Этот класс обрабатывает HTTP-запросы и возвращает JSON
@RequestMapping("/api/cars") // Базовый URL. Все методы будут начинаться с /api/cars
public class CarController {
    private List<Car> cars = new ArrayList<>();

    public CarController() {
        cars.add(new Car(1L, "Toyota", "Camry", 2020, 25000));
        cars.add(new Car(2L, "BMW", "X5", 2022, 60000));
    }

    // 1. Получение всех объектов (GET /api/cars)
    @GetMapping
    public List<Car> getAllCars() {
        return cars;
    }

    // 2. Получение объекта по ID (GET /api/cars/{id})
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 3. Добавление нового элемента (POST /api/cars)
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        // В теле запроса (Body) приходит JSON, он превращается в объект Car
        cars.add(car);
        return car; // Возвращаем добавленный объект
    }

    // 4. Обновление элемента (PUT /api/cars/{id})
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car> carOptional = cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (carOptional.isPresent()) {
            Car existingCar = carOptional.get();
            // Обновляем поля
            existingCar.setBrand(carDetails.getBrand());
            existingCar.setModel(carDetails.getModel());
            existingCar.setYear(carDetails.getYear());
            existingCar.setPrice(carDetails.getPrice());
            return existingCar;
        }
        return null;
    }

    // 5. Удаление элемента (DELETE /api/cars/{id})
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        cars.removeIf(c -> c.getId().equals(id));
    }
}