package springBoot.domain;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String brand;
    private String model;
    private int power;
    private int price;
    public Car(String mark, String model, int power, int price){
        this.brand = mark;
        this.model = model;
        this.power = power;
        this.price = price;
    }
    public Car() {}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String mark) {
        this.brand = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
