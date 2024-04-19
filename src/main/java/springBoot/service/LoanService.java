package springBoot.service;

import org.springframework.stereotype.Service;
import springBoot.configuration.UserConfigProperties;
import springBoot.domain.Car;
import springBoot.domain.User;

@Service
public class LoanService {
    private final UserConfigProperties userConfigProperties;
    private final UserService userService;
    private final IncomeService incomeService;


    public LoanService(UserConfigProperties userConfigProperties, UserService userService, IncomeService incomeService) {
        this.userConfigProperties = userConfigProperties;
        this.userService = userService;
        this.incomeService = incomeService;
    }

    public double countValueOfMaxCredit(long id) {
        if (canGetCredit(id)) {
            return getSumOfMaxCredit(id);
        } else return 0;
    }

    private boolean canGetCredit(long id) {
        long income = incomeService.getUserIncome(id);
        Car car = userService.getUser(id).getCar();
        if (car != null) {
            long carPrice = car.getPrice();
            return income > userConfigProperties.getMinimalIncome() || carPrice > userConfigProperties.getMinCarPrice();
        }
        return income > userConfigProperties.getMinimalIncome();
    }

    private double getSumOfMaxCredit(long id) {
        long income = incomeService.getUserIncome(id);
        int valueOfMonthIncome = userConfigProperties.getValueOfMonthIncome();
        User user = userService.getUser(id);
        int carPrice = user.getCar().getPrice();
        double coefficientOfCarPrice = userConfigProperties.getCoefficientOfCarPrice();
        if (user.getCar() == null ||
                income * valueOfMonthIncome > carPrice * coefficientOfCarPrice) {
            return income * valueOfMonthIncome;
        } else {
            return carPrice * coefficientOfCarPrice;
        }
    }
}
