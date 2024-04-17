package springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import springBoot.domain.Car;
import springBoot.domain.User;
import springBoot.repos.CarRepo;
import springBoot.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/loan")
    public String showUser(@RequestParam(value = "userId", required = true, defaultValue = "-1") long id, Model model) {
        double credit = userService.countValueOfMaxCredit(id);
        model.addAttribute("credit", credit);
        return "user";
    }
}
