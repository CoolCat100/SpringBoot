package springBoot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("user.loan")
public class UserConfigProperties {
    private int minimalIncome;
    private int minCarPrice;
    private int valueOfIncomeMonth;
    private double coefficientOfCarPrice;

    public int getMinimalIncome() {
        return minimalIncome;
    }

    public void setMinimalIncome(int minimalIncome) {
        this.minimalIncome = minimalIncome;
    }

    public int getMinCarPrice() {
        return minCarPrice;
    }

    public void setMinCarPrice(int minCarPrice) {
        this.minCarPrice = minCarPrice;
    }

    public int getValueOfIncomeMonth() {
        return valueOfIncomeMonth;
    }

    public void setValueOfIncomeMonth(int valueOfIncomeMonth) {
        this.valueOfIncomeMonth = valueOfIncomeMonth;
    }

    public double getCoefficientOfCarPrice() {
        return coefficientOfCarPrice;
    }

    public void setCoefficientOfCarPrice(double coefficientOfCarPrice) {
        this.coefficientOfCarPrice = coefficientOfCarPrice;
    }
}
