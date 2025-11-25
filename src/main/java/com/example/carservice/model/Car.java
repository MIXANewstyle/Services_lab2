package com.example.carservice.model;

public class Car {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private double price;

    // 1. Пустой конструктор (обязателен для JSON-сериализации)
    public Car() {
    }

    // 2. Полный конструктор (для создания объектов в контроллере)
    public Car(Long id, String brand, String model, int year, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    // 3. Геттеры и Сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}