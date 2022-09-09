package thedevelopers.project.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.Transaction;
import thedevelopers.project.demo.services.EnterpriseService;
import thedevelopers.project.demo.services.TransactionService;

import java.util.List;

@Slf4j
@Controller
@RestController
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/enterprises")
    public List<Enterprise> getEnterpriseList(){
        log.info(enterpriseService.getAll() + "");
        return enterpriseService.getAll();
    }

    @PostMapping("/enterprises")
    public Enterprise createEnterprise(@RequestBody Enterprise enterprise){
        return enterpriseService.createElement(enterprise);
    }

    @GetMapping("/enterprises/{id}")
    public Enterprise getEnterprise(@PathVariable String id){
        return enterpriseService.getElement(id);
    }

    @PatchMapping("/enterprises/{id}")
    public Enterprise updateEnterprise(@RequestBody Enterprise enterprise,@PathVariable String id){
        Enterprise enterpriseFound = enterpriseService.getElement(id);
        log.info(enterprise + "");
        if(enterpriseFound != null){
            return enterpriseService.updateElement(enterpriseFound, enterprise);
        }
        return null;
    }

    @DeleteMapping("/enterprises/{id}")
    public String deleteEnterprise(@PathVariable String id){
        Enterprise enterprise = enterpriseService.getElement(id);
        if (enterprise != null){
            enterpriseService.deleteElement(enterprise);
            return "Empresa eliminada con exito";
        }
        return "No se pudo eliminar el elemento, no existe";
    }
}
