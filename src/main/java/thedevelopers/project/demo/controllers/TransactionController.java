package thedevelopers.project.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.Transaction;
import thedevelopers.project.demo.services.EmployeeService;
import thedevelopers.project.demo.services.EnterpriseService;
import thedevelopers.project.demo.services.TransactionService;

import java.util.List;

@Slf4j
@Controller
@RestController
public class TransactionController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/movements")
    public List<Transaction> getAllMovements(){
        return transactionService.getAll();
    }

    @GetMapping("/enterprises/{id}/movements")
    public List<Transaction> getEnterpriseMovements(@PathVariable Long id){
        return transactionService.getAllEnterpriseMovements(id);
    }

    @GetMapping("/enterprises/{id}/movements/{idTransaction}")
    public Transaction getEnterpriseSpecificMovement(@PathVariable(value = "id") Long idEnterprise, @PathVariable(value = "idTransaction") String idTransaction){
        Transaction transactionFound = transactionService.getElement(idTransaction);
        if(transactionFound.getEnterpriseTransaction().getIdEnterprise() == idEnterprise){
            return transactionFound;
        }
        return null;
    }

    @PostMapping("/enterprises/{id}/movements")
    public Transaction createEnterpriseMovement(@RequestBody Transaction transaction){
        transaction.setEnterpriseTransaction(enterpriseService.getElement(String.valueOf(transaction.getEnterpriseTransaction().getIdEnterprise())));
        transaction.setEmployeeTransaction(employeeService.getElement(String.valueOf(transaction.getEmployeeTransaction().getIdEmployee())));
        return transactionService.createElement(transaction);
    }

    @PatchMapping("/enterprises/{id}/movements/{idTransaction}")
    public Transaction updateEnterpriseMovement(@RequestBody Transaction transaction,@PathVariable(value = "id") Long idEnterprise, @PathVariable(value = "idTransaction") String idTransaction){
        Transaction transactionFound = transactionService.getElement(idTransaction);
        if(transactionFound != null && transactionFound.getEnterpriseTransaction().getIdEnterprise() == idEnterprise){
            return transactionService.updateElement(transactionFound, transaction);
        }
        return null;
    }

    @DeleteMapping("/enterprises/{id}/movements/{idTransaction}")
    public String deleteEnterpriseMovement(@PathVariable(value = "id") Long idEnterprise, @PathVariable(value = "idTransaction") String idTransaction){
        Transaction transaction = transactionService.getElement(idTransaction);
        if (transaction != null && transaction.getEnterpriseTransaction().getIdEnterprise() == idEnterprise){
            transactionService.deleteElement(transaction);
            return "Transacción eliminada con exito";
        }
        return "No se pudo eliminar el elemento, no existe";
    }
}
