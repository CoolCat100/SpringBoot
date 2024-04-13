package springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBoot.Services.CarService;
import springBoot.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarsController {
    @Autowired
    CarService carService;
    @Value("${car.maxCars}")
    private int maxCars;
    @Value("${car.sorting.fields}")
    private String allFilters;

    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "count", required = false, defaultValue = "-1") int value, @RequestParam(name = "sortBy", required = false) String name, Model model) {
        List<Car> cars = null;
        if (name == null) {
            if (value == -1 || value >= maxCars) {
                cars = carService.getAll();
            } else {
                cars = carService.getLimit(value);
            }
        } else {
            boolean trueFilter = false;
            if (value == -1 || value >= maxCars) {
                value = Integer.MAX_VALUE;
            }
            System.out.println(allFilters);
            List<String> filters = List.of(allFilters.split(", "));
            for (String filter : filters) {
                System.out.println(filter);
                if (name.equals(filter) && filter.equals("brand")) {
                    cars = carService.getCarRepo().findAllByOrderByBrandAsc().stream().limit(value).collect(Collectors.toList());
                    trueFilter = true;
                    break;
                }
                if (name.equals(filter) && filter.equals("model")) {
                    cars = carService.getCarRepo().findAllByOrderByModelAsc().stream().limit(value).collect(Collectors.toList());
                    trueFilter = true;
                    break;
                }
                if (name.equals(filter) && filter.equals("power")) {
                    cars = carService.getCarRepo().findAllByOrderByPowerAsc().stream().limit(value).collect(Collectors.toList());
                    trueFilter = true;
                    break;
                }
            }
            if (!trueFilter) {
                throw new BadRequestException();
            }
        }
        model.addAttribute("list", cars);
        return "cars";
    }
}
