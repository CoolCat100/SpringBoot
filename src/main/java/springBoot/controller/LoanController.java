package springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBoot.service.LoanService;
import springBoot.service.UserService;

@Controller
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("/loan")
    public String showUser(@RequestParam(value = "userId") long id, Model model) {
        double credit = loanService.countValueOfMaxCredit(id);
        model.addAttribute("credit", credit);
        return "loan";
    }
}
