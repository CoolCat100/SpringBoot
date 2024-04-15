package springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBoot.service.CarService;
import springBoot.domain.Car;

import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "count", required = false, defaultValue = "-1") int limit,
                           @RequestParam(name = "sortBy", required = false) String sortBy, Model model) {
        List<Car> cars = carService.getCars(limit, sortBy);
        model.addAttribute("list", cars);
        return "cars";
    }
}
