package springBoot.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import service.IncomeClient;
import springBoot.configuration.UserConfigProperties;

@Service
@AllArgsConstructor
public class LoanService {
    private final UserConfigProperties userConfigProperties;
    private final UserService userService;
    private final IncomeClient incomeClient;


    public double countValueOfMaxCredit(long id) {
        long income = incomeClient.getUserIncome(id);
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
