package springBoot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String brand;
    private String model;
    private int power;
    private int price;

    public Car(String mark, String model, int power, int price) {
        this.brand = mark;
        this.model = model;
        this.power = power;
        this.price = price;
    }
}
