package springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBoot.Services.CarService;
import springBoot.domain.Car;

import java.util.List;

@Controller
public class CarsController {
    @Autowired
    CarService carService;
    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "count", required = false, defaultValue = "10") int value, Model model) {
        List<Car> list = carService.getAll();
        for (Car car : list) {
            System.out.println(car.getBrand());
            System.out.println(car.getModel());
            System.out.println(car.getPower());
            System.out.println();
        }
        model.addAttribute("list", list);
        return "cars";
    }
}
