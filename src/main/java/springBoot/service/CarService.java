package springBoot.service;

import org.springframework.beans.factory.annotation.Value;
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

    public List<Car> getAll() {
        return (List<Car>) carRepo.findAll();
    }

    public List<Car> getLimit(int limit) {
        return getAll().stream().limit(limit).collect(Collectors.toList());
    }
    public List<Car> sortByBrand(int value) {
        return carRepo.findAllByOrderByBrandAsc().stream().limit(value).collect(Collectors.toList());
    }
    public List<Car> sortByModel(int value) {
        return carRepo.findAllByOrderByModelAsc().stream().limit(value).collect(Collectors.toList());
    }
    public List<Car> sortByPower(int value) {
        return carRepo.findAllByOrderByPowerAsc().stream().limit(value).collect(Collectors.toList());
    }
    public List<Car> showCars(int value) {
        List<Car> cars = null;
        if (value == -1 || value >= maxCars) {
            cars = getAll();
        } else {
            cars = getLimit(value);
        }
        return cars;
    }
    public List<Car> showSortedCars(int value, String name) {
        List<Car> cars = null;
        boolean trueFilter = false;
        if (value == -1 || value >= maxCars) {
            value = Integer.MAX_VALUE;
        }
        List<String> filters = List.of(allFilters.split(", "));
        for (String filter : filters) {
            if (name.equals(filter) && filter.equals("brand")) {
                cars = sortByBrand(value);
                trueFilter = true;
                break;
            }
            if (name.equals(filter) && filter.equals("model")) {
                cars = sortByModel(value);
                trueFilter = true;
                break;
            }
            if (name.equals(filter) && filter.equals("power")) {
                cars = sortByPower(value);
                trueFilter = true;
                break;
            }
        }
        if (!trueFilter) {
            throw new BadRequestException();
        }
        return cars;
    }
}
