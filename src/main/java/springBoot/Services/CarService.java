package springBoot.Services;

import org.springframework.stereotype.Service;
import springBoot.domain.Car;
import springBoot.repos.CarRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepo carRepo;
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }
    public List<Car> getAll(){
        return (List<Car>) carRepo.findAll();
    }
    public List<Car> getLimit(int limit) {
        List<Car> list = new ArrayList<>();
        for (long x = 1; x <= limit; x++) {
            if (carRepo.findById(x).isPresent()) {
                list.add(carRepo.findById(x).get());
            }
        }
        return list;
    }
}
