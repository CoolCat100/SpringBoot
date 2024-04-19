package springBoot.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springBoot.configuration.UserConfigProperties;
import springBoot.domain.Car;
import springBoot.domain.User;
import springBoot.exception.BadRequestException;
import springBoot.repos.CarRepo;
import springBoot.repos.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUser(long id) {
        if (id <= 0) {
            throw new BadRequestException();
        }
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new BadRequestException();
        }
        return user;
    }
}
