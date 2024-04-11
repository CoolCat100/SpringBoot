package springBoot.Services;

import org.springframework.stereotype.Service;
import springBoot.domain.Car;
import springBoot.repos.CarRepo;

import java.util.List;

@Service
public class CarService {
    private final CarRepo carRepo;
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }
    public List<Car> getAll(){
        return (List<Car>) carRepo.findAll();
    }
}
