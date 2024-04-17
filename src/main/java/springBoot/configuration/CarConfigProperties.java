package springBoot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("car")
public class CarConfigProperties {
    private int maxCars;
    private List<String> sorting;

    public int getMaxCars() {
        return maxCars;
    }

    public List<String> getSorting() {
        return sorting;
    }

    public void setSorting(List<String> sorting) {
        this.sorting = sorting;
    }

    public void setMaxCars(int maxCars) {
        this.maxCars = maxCars;
    }
}
