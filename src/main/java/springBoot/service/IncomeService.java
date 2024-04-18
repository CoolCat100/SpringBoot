package springBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springBoot.domain.User;
import springBoot.dto.UserDto;

@Service
public class IncomeService {
    public UserDto getUserIncome(long id, UserDto userDto) {
        RestTemplate restTemplate = new RestTemplate();
        UserDto[] users = restTemplate.getForObject("https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income", UserDto[].class);
        for (UserDto user : users) {
            if (user.getId() == id) {
                userDto.setIncome(user.getIncome());
            }
        }
        return userDto;
    }
}
