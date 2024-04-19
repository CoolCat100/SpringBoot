package springBoot.dto;

import springBoot.domain.Car;

public class UserDto {
    private long id;
    private long income;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }
}
