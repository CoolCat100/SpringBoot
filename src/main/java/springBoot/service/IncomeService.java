package springBoot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springBoot.configuration.UserConfigProperties;
import springBoot.dto.UserDto;

import java.util.List;

@Service
@AllArgsConstructor
public class IncomeService {
    private final RestTemplate restTemplate;
    private final UserConfigProperties userConfigProperties;


    public long getUserIncome(long id) {
        List<UserDto> users = getUsersDto();
        for (UserDto user : users) {
            if (user.getId() == id) {
                return user.getIncome();
            }
        }
        return 0;
    }

    private List<UserDto> getUsersDto() {
        return List.of(restTemplate.getForObject(userConfigProperties.getIncomeUrl(), UserDto[].class));
    }
}
