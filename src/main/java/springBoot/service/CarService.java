package springBoot.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springBoot.configuration.CarConfigProperties;
import springBoot.domain.Car;
import springBoot.exception.BadRequestException;
import springBoot.repos.CarRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService {
    private final CarConfigProperties configProperties;
    private final CarRepo carRepo;

    public List<Car> getCars(int limit, String sortBy) {
        if (sortBy == null) {
            return getLimitCar(limit);
        } else {
            return getSortedCar(limit, sortBy);
        }
    }

    public List<Car> getLimitCar(int limit) {
        limit = checkLimit(limit);
        List<Car> cars = (List<Car>) carRepo.findAll();
        return cars.stream().limit(limit).collect(Collectors.toList());
    }

    public List<Car> getSortedCar(int limit, String sortBy) {
        limit = checkLimit(limit);
        List<String> filters = configProperties.getSorting();
        if (filters.contains(sortBy)) {
            List<Car> cars = (List<Car>) carRepo.findAll(Sort.by(sortBy).ascending());
            return cars.stream().limit(limit).collect(Collectors.toList());
        } else {
            throw new BadRequestException();
        }
    }

    private int checkLimit(int limit) {
        if (limit == -1 || limit >= configProperties.getMaxCars()) {
            return Integer.MAX_VALUE;
        } else {
            return limit;
        }
    }
}
