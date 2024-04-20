package springBoot.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("user.loan")
@Setter
@Getter
public class UserConfigProperties {
    private int minimalIncome;
    private int minCarPrice;
    private int valueOfMonthIncome;
    private double coefficientOfCarPrice;
    private String IncomeUrl;
}
