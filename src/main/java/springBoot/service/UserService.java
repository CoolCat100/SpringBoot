package springBoot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springBoot.domain.Car;
import springBoot.domain.User;
import springBoot.exception.BadRequestException;
import springBoot.repos.UserRepo;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

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

    public int getUserCarPrice(long id) {
        User user = getUser(id);
        Car car = user.getCar();
        if (car == null) {
            return 0;
        } else {
            return car.getPrice();
        }
    }
}
