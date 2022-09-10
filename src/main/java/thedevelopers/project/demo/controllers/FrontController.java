package thedevelopers.project.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.Transaction;
import thedevelopers.project.demo.services.EmployeeService;
import thedevelopers.project.demo.services.EnterpriseService;
import thedevelopers.project.demo.services.TransactionService;

import java.util.List;

/*
@Controller
public class FrontController {

    private EmployeeService em_service;
    private EnterpriseService en_service;
    private TransactionService tr_service;

    public FrontController(TransactionService tr_service, EmployeeService em_service, EnterpriseService en_service){
        this.tr_service = tr_service;
        this.em_service = em_service;
        this.en_service = en_service;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/income")
    public String income(Model model){
        List<Transaction> income = this.tr_service.getAll();
        model.addAttribute("income", income);
        return "income";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<Employee> users = this.em_service.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/enterprises")
    public String enterprises(Model model){
        List<Enterprise> enterprises = this.en_service.getAll();
        model.addAttribute("enterprises", enterprises);
        return "enterprises";
    }

}

 */
