package thedevelopers.project.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.Transaction;
import thedevelopers.project.demo.services.EmployeeService;
import thedevelopers.project.demo.services.EnterpriseService;
import thedevelopers.project.demo.services.TransactionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private EmployeeService employeeService;
    private Employee employee;

    @GetMapping("/movements")
    public String getAllMovements(Model model, @AuthenticationPrincipal OidcUser principal){
        employee = employeeService.getEmployee(principal.getClaims());
        List<Transaction> enterpriseTransactions = new ArrayList<>();
        if(employee.getEnterpriseEmployee() != null){
            enterpriseTransactions = transactionService.getAllEnterpriseMovements(employee.getEnterpriseEmployee().getIdEnterprise());
        }
        boolean isAdmin = employee.getRoleName().getTextName().equalsIgnoreCase("ADMIN");
        model.addAttribute("movementList", enterpriseTransactions);
        model.addAttribute("employee", this.employee);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("totalAmount", transactionService.getTotalAmount(enterpriseTransactions));
        return "income";
    }

    @GetMapping("/new_movement")
    public String createEnterpriseMovement(Model model, @AuthenticationPrincipal OidcUser principal, RedirectAttributes redirectAttrs){
        this.employee = employeeService.getEmployee(principal.getClaims());
        if(this.employee.getEnterpriseEmployee() == null){
            redirectAttrs.addFlashAttribute("mensaje", "No se puede crear una transacción porque el usuario no tiene una empresa asociada");
            return "redirect:/movements";
        }
        model.addAttribute("employee", this.employee);
        model.addAttribute("userTransaction", this.employee);
        model.addAttribute("enterpriseTransaction", this.employee.getEnterpriseEmployee());
        return "new-income";
    }

    @PostMapping("/movements")
    public String saveNewEnterpriseMovement(@Valid Transaction transaction, @AuthenticationPrincipal OidcUser principal, RedirectAttributes redirectAttrs){
        Employee loginUser = employeeService.getEmployee(principal.getClaims());
        transaction.setEnterpriseTransaction(loginUser.getEnterpriseEmployee());
        transaction.setEmployeeTransaction(loginUser);
        transactionService.createElement(transaction);
        redirectAttrs.addFlashAttribute("mensaje", "Transacción creada con éxito");
        redirectAttrs.addFlashAttribute("clase", "success");
        return "redirect:/movements";
    }

    @GetMapping("/update_movement")
    public String updateEnterpriseMovement(Transaction transaction, Model model){
        model.addAttribute("employee", this.employee);
        Transaction transactionFound = transactionService.getElement(String.valueOf(transaction.getIdTransaction()));
        model.addAttribute("transactionData", transactionFound);
        return "update-income";
    }

    @PatchMapping("/movements/{id}")
    public String saveChangesEnterpriseMovement(@Valid Transaction transaction, @PathVariable(value = "id") String idTransaction, RedirectAttributes redirectAttrs){
        Transaction transactionFound = transactionService.getElement(idTransaction);
        if(transactionFound != null){
            transactionService.updateElement(transactionFound, transaction);
            redirectAttrs.addFlashAttribute("mensaje", "Transacción actualizada con éxito");
            redirectAttrs.addFlashAttribute("clase", "success");
            return "redirect:/movements";
        }
        return "redirect:/movements";
    }

    @DeleteMapping("/movements/{idTransaction}")
    public String deleteEnterpriseMovement(@PathVariable(value = "idTransaction") String idTransaction, RedirectAttributes redirectAttrs){
        Transaction transaction = transactionService.getElement(idTransaction);
        if (transaction != null){
            transaction.setEmployeeTransaction(null);
            transaction.setEnterpriseTransaction(null);
            transactionService.deleteElement(transaction);
            redirectAttrs.addFlashAttribute("mensaje", "Transacción eliminada con éxito");
            redirectAttrs.addFlashAttribute("clase", "success");
            return "redirect:/movements";
        }
        return "redirect:/movements";
    }
}