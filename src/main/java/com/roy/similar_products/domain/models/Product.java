package com.roy.similar_products.domain.models;

public class Product {
  String id;
  String name;
  Double price;
  String availability;

  public Product(String id, String name, Double price, String availability) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.availability = availability;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getAvailability() {
    return availability;
  }

  public void setAvailability(String availability) {
    this.availability = availability;
  }

}
