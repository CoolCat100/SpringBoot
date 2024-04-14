package springBoot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springBoot.domain.Car;
import springBoot.exception.BadRequestException;
import springBoot.repos.CarRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepo carRepo;
    @Value("${car.maxCars}")
    private int maxCars;
    @Value("${car.sorting.fields}")
    private String allFilters;

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

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
        List<String> filters = List.of(allFilters.split(", "));
        boolean trueFilter = false;
        for (String filter : filters) {
            if (sortBy.equals(filter)) {
                trueFilter = true;
            }
        }
        if (!trueFilter) {
            throw new BadRequestException();
        }
        List<Car> cars = (List<Car>) carRepo.findAll(Sort.by(sortBy).ascending());
        return cars.stream().limit(limit).collect(Collectors.toList());
    }

    private int checkLimit(int limit) {
        if (limit == -1 || limit >= maxCars) {
            return Integer.MAX_VALUE;
        } else {
            return limit;
        }
    }
}
