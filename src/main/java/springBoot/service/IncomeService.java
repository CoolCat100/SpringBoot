package springBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springBoot.configuration.UserConfigProperties;
import springBoot.dto.UserDto;
import springBoot.utils.MappingUtils;

import java.util.List;

@Service
public class IncomeService {
    private final RestTemplate restTemplate;
    private final UserConfigProperties userConfigProperties;
    private final UserService userService;


    public IncomeService(RestTemplate restTemplate, UserConfigProperties userConfigProperties, UserService userService, MappingUtils mappingUtils) {
        this.restTemplate = restTemplate;
        this.userConfigProperties = userConfigProperties;
        this.userService = userService;
    }

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
