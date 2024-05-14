package entity;
//package chua cac class

public class Product {
  private int id;
  private String name;
  private String image;
  private double price;
  private Category category;
  private String description;

  public Product(int id, String name, String image, double price, Category category, String description) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.price = price;
    this.category = category;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Product{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", image='" + image + '\'' +
      ", price=" + price +
      ", category=" + category +
      ", description='" + description + '\'' +
      '}';
  }
}

