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


    public LoanService(UserConfigProperties userConfigProperties, UserService userService, IncomeService incomeService, MappingUtils mappingUtils, MappingUtils mappingUtils1) {
        this.userConfigProperties = userConfigProperties;
        this.userService = userService;
        this.incomeService = incomeService;
        this.mappingUtils = mappingUtils1;
    }

    public double countValueOfMaxCredit(long id) {
        User user = userService.getUser(id);
        UserDto userDto = mappingUtils.mapToUserDto(user);
        userDto.setIncome(incomeService.getUserIncome(id));
        if (canGetCredit(userDto)) {
            return getSumOfMaxCredit(userDto);
        } else return 0;
    }

    private boolean canGetCredit(UserDto user) {
        long income = user.getIncome();
        if (user.getCar() != null) {
            long carPrice = user.getCar().getPrice();
            return income > userConfigProperties.getMinimalIncome() || carPrice > userConfigProperties.getMinCarPrice();
        }
        return income > userConfigProperties.getMinimalIncome();
    }

    private double getSumOfMaxCredit(UserDto user) {
        int valueOfMonthIncome = userConfigProperties.getValueOfMonthIncome();
        int carPrice = user.getCar().getPrice();
        double coefficientOfCarPrice = userConfigProperties.getCoefficientOfCarPrice();
        if (user.getCar() == null ||
                user.getIncome() * valueOfMonthIncome > carPrice * coefficientOfCarPrice) {
            return user.getIncome() * valueOfMonthIncome;
        } else {
            return carPrice * coefficientOfCarPrice;
        }
    }
}
