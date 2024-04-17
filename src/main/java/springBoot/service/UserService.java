package springBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springBoot.configuration.UserConfigProperties;
import springBoot.domain.Car;
import springBoot.domain.User;
import springBoot.exception.BadRequestException;
import springBoot.repos.CarRepo;

@Service
public class UserService {
    private final UserConfigProperties userConfigProperties;
    private final CarRepo carRepo;

    public UserService(UserConfigProperties userConfigProperties, CarRepo carRepo) {
        this.userConfigProperties = userConfigProperties;
        this.carRepo = carRepo;
    }
    public double countValueOfMaxCredit(long id){
        User user = getUser(id);
        if (solvency(user)) {
            return getSumOfMaxCredit(user);
        } else return 0;
    }

    public User getUser(long id) {
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject("https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income", User[].class);
        for (User user : users) {
            if (user.getId() == id) {
                Car car = carRepo.findById(id).orElse(new Car("-", "-", 0, 0));
                user.setCarPrice(car.getPrice());
                return user;
            }
        }
        throw new BadRequestException();
    }

    private boolean solvency(User user) {
        return user.getIncome() > userConfigProperties.getMinimalIncome() || user.getCarPrice() > userConfigProperties.getMinCarPrice();
    }
    private double getSumOfMaxCredit(User user) {
        if (user.getIncome() * userConfigProperties.getValueOfIncomeMonth() > user.getCarPrice() * userConfigProperties.getCoefficientOfCarPrice()) {
            return user.getIncome() * userConfigProperties.getValueOfIncomeMonth();
        } else {
            return user.getCarPrice() * userConfigProperties.getCoefficientOfCarPrice();
        }
    }
}
