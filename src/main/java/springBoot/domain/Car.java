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
    public Car(String mark, String model, int power){
        this.brand = mark;
        this.model = model;
        this.power = power;
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
}
