package springBoot.service;

import org.springframework.stereotype.Service;
import springBoot.configuration.UserConfigProperties;
import springBoot.domain.User;
import springBoot.dto.UserDto;
import springBoot.utils.MappingUtils;

@Service
public class LoanService {
    private final UserConfigProperties userConfigProperties;
    private final UserService userService;
    private final IncomeService incomeService;
    private final MappingUtils mappingUtils;

    public LoanService(UserConfigProperties userConfigProperties, UserService userService, IncomeService incomeService, MappingUtils mappingUtils) {
        this.userConfigProperties = userConfigProperties;
        this.userService = userService;
        this.incomeService = incomeService;
        this.mappingUtils = mappingUtils;
    }

    private boolean solvency(UserDto user) {
        if (user.getCar() != null) {
            return user.getIncome() > userConfigProperties.getMinimalIncome() || user.getCar().getPrice() > userConfigProperties.getMinCarPrice();
        }
        return user.getIncome() > userConfigProperties.getMinimalIncome();
    }
    private double getSumOfMaxCredit(UserDto user) {
        if (user.getCar() == null ||
                user.getIncome() * userConfigProperties.getValueOfIncomeMonth() > user.getCar().getPrice() * userConfigProperties.getCoefficientOfCarPrice()) {
            return user.getIncome() * userConfigProperties.getValueOfIncomeMonth();
        } else {
            return user.getCar().getPrice() * userConfigProperties.getCoefficientOfCarPrice();
        }
    }
    public double countValueOfMaxCredit(long id) {
        UserDto user = incomeService.getUserIncome(id, mappingUtils.mapToProductDto(userService.getUser(id)));
        if (solvency(user)) {
            return getSumOfMaxCredit(user);
        } else return 0;
    }
}
