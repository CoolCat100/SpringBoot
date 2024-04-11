package springBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBoot.Services.CarService;
import springBoot.domain.Car;
import org.springframework.web.bind.annotation.ResponseBody;
import springBoot.repos.CarRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CarsController {
    @Autowired
    CarService carService;
    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "count", required = false, defaultValue = "10") int value, Map<String, Object> model, Model model2) {
        List<Car> list = carService.getAll();
        for (Car car : list) {
            System.out.println(car.getMark());
            System.out.println(car.getModel());
            System.out.println(car.getPower());
        }
        model2.addAttribute("list", list);
        return "cars";
    }
}
