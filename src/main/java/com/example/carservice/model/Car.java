package com.example.carservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Document обозначает Спрингу: "Этот класс нужно хранить в MongoDB в коллекции 'cars'"
@Document(collection = "cars")
public class Car {

    @Id // первичный ключ в базе данных
    private String id;

    private String brand;
    private String model;
    private int year;
    private double price;

    public Car() {
    }

    public Car(String id, String brand, String model, int year, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}