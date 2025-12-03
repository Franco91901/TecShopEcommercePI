package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String marca;
    private String model;
    private String year;
    private String ram;
    private String storage;
    private String processor;
    private String imageUrl;

    public Producto() {
    }

    public Producto(Long id, String name, String description, double price, String marca, String model, String year, String ram, String storage, String processor, String imageUrl) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.marca = marca;
        this.model = model;
        this.year = year;
        this.ram = ram;
        this.storage = storage;
        this.processor = processor;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", processor='" + processor + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
