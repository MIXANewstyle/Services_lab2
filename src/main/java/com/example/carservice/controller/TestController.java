package com.example.carservice.controller;

import com.example.carservice.model.Car;
import com.example.carservice.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final CarRepository carRepository;

    public TestController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Тест 1: Добавление N случайных машин
    @PostMapping("/add/{count}")
    public String addCars(@PathVariable int count) {
        List<Car> cars = new ArrayList<>();

        // 1. Генерируем список машин в памяти
        for (int i = 0; i < count; i++) {
            cars.add(new Car(
                    null, // ID пусть генерирует база
                    "Brand-" + i,
                    "Model-" + i,
                    2000 + (i % 24), // Год от 2000 до 2023
                    10000 + i
            ));
        }

        // 2. Засекаем время начала
        long startTime = System.currentTimeMillis();

        // 3. Сохраняем ВСЕ разом (saveAll быстрее, чем save по одному)
        carRepository.saveAll(cars);

        // 4. Засекаем время конца
        long endTime = System.currentTimeMillis();

        // 5. Считаем разницу
        long duration = endTime - startTime;

        return "Успешно добавлено " + count + " машин за " + duration + " мс (" + (duration / 1000.0) + " сек).";
    }

    // Тест 2: Удаление всех машин
    @DeleteMapping("/clear")
    public String clearDatabase() {
        long startTime = System.currentTimeMillis();

        long countBefore = carRepository.count(); // Узнаем, сколько было
        carRepository.deleteAll(); // Удаляем всё

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        return "Удалено " + countBefore + " записей за " + duration + " мс.";
    }
}