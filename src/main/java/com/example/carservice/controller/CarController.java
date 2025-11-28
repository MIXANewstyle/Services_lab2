package com.example.carservice.controller;

import com.example.carservice.model.Car;
import com.example.carservice.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    // Ссылка на интерфейс репозитория
    private final CarRepository carRepository;

    // Внедрение через конструктор
    // Spring видит этот конструктор, находит созданный им бин CarRepository и передает сюда.
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // 1. Получение всех (GET)
    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll(); // Метод findAll() уже есть в MongoRepository
    }

    // 2. Получение по ID (GET)
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable String id) {
        return carRepository.findById(id).orElse(null);
    }

    // 3. Добавление (POST)
    @PostMapping
    public Car addCar(@RequestBody Car car) {
        // Если ID не передан, MongoDB сгенерирует его сама.
        return carRepository.save(car); // Метод save() сохраняет или обновляет
    }

    // 4. Обновление (PUT)
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable String id, @RequestBody Car carDetails) {
        Optional<Car> carOptional = carRepository.findById(id);

        if (carOptional.isPresent()) {
            Car existingCar = carOptional.get();
            existingCar.setBrand(carDetails.getBrand());
            existingCar.setModel(carDetails.getModel());
            existingCar.setYear(carDetails.getYear());
            existingCar.setPrice(carDetails.getPrice());
            // save() работает умно: если ID есть в базе — обновляет, если нет — создает.
            return carRepository.save(existingCar);
        }
        return null;
    }

    // 5. Удаление (DELETE)
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String id) {
        carRepository.deleteById(id);
    }
}