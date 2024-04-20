package springBoot.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("car")
@Getter
@Setter
public class CarConfigProperties {
    private int maxCars;
    private List<String> sorting;
}
