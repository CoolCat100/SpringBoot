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
        long income = incomeService.getUserIncome(id);
        int carPrice = userService.getUserCarPrice(id);
        if (canGetCredit(income, carPrice)) {
            return getSumOfMaxCredit(income, carPrice);
        } else return 0;
    }

    private boolean canGetCredit(long income, int carPrice) {
            return income > userConfigProperties.getMinimalIncome() || carPrice > userConfigProperties.getMinCarPrice();
    }

    private double getSumOfMaxCredit(long income, int carPrice) {
        int valueOfMonthIncome = userConfigProperties.getValueOfMonthIncome();
        double coefficientOfCarPrice = userConfigProperties.getCoefficientOfCarPrice();
        if (income * valueOfMonthIncome > carPrice * coefficientOfCarPrice) {
            return income * valueOfMonthIncome;
        } else {
            return carPrice * coefficientOfCarPrice;
        }
    }
}
