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

import java.util.List;

@Slf4j
@Controller
//@RestController
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/enterprises")
    public String getEnterpriseList(Model model, @AuthenticationPrincipal OidcUser principal){
        model.addAttribute("enterpriseList", enterpriseService.getAll());
        boolean isAdmin = employeeService.getEmployee(principal.getClaims()).getRoleName().getTextName().equalsIgnoreCase("ADMIN");
        model.addAttribute("isAdmin", isAdmin);
        return "enterprises";
    }

    @GetMapping("/new_enterprise")
    public String createEnterprise(Enterprise enterprise, Model model, String message){
        if(enterprise.getNameEnterprise() != null){
            model.addAttribute("mensaje", "El " + message + " que intenta registrar ya existe");
        }
        model.addAttribute("enterpriseData", enterprise);
        return "new-enterprise";
    }

    @PostMapping("/enterprises")
    public String saveNewEnterprise(@Valid Enterprise enterprise, RedirectAttributes redirectAttrs, Model model){
        String validation = enterpriseService.findDocumentAndName(enterprise.getNameEnterprise(),enterprise.getDocumentEnterprise());
        if(validation.equals("")){
            enterpriseService.createElement(enterprise);
            redirectAttrs.addFlashAttribute("mensaje", "Empresa creado con éxito");
            redirectAttrs.addFlashAttribute("clase", "success");
        }else{
            return this.createEnterprise(enterprise, model, validation);
        }
        return "redirect:/enterprises";
    }

    @GetMapping("/enterprises/{id}")
    public Enterprise getEnterprise(@PathVariable String id){
        return enterpriseService.getElement(id);
    }

    @GetMapping("/update_enterprise/{id}")
    public String updateEnterprise(@PathVariable String id, Model model, Enterprise newEnterprise, String message){
        Enterprise enterprise = enterpriseService.getElement(id);
        if(newEnterprise.getNameEnterprise() != null){
            model.addAttribute("mensaje", "El " + message + " que intenta registrar ya existe");
        }
        model.addAttribute("enterpriseData", enterprise);
        return "update-enterprise";
    }

    @PatchMapping("/enterprises/{id}")
    public String saveChangesEnterprise(@Valid Enterprise enterprise,@PathVariable String id, Model model,RedirectAttributes redirectAttrs){
        Enterprise enterpriseFound = enterpriseService.getElement(id);
        String messageData = enterpriseService.validateData(enterpriseFound, enterprise);
        if(messageData.equals("")){
            if(enterpriseFound != null){
                enterpriseService.updateElement(enterpriseFound, enterprise);
                redirectAttrs.addFlashAttribute("mensaje", "Empresa actualizada con éxito");
                redirectAttrs.addFlashAttribute("clase", "success");
            }
        }else{
            return this.updateEnterprise(id, model, enterprise, messageData);
        }
        return "redirect:/enterprises";
    }

    @DeleteMapping("/enterprises/{id}")
    public String deleteEnterprise(@PathVariable String id, RedirectAttributes redirectAttrs){
        if(enterpriseService.haveAsociations(id)){
            redirectAttrs.addFlashAttribute("mensaje", "La empresa no se puede eliminar \n porque tiene elementos asociados");
        }else{
            Enterprise enterprise = enterpriseService.getElement(id);
            if (enterprise != null){
                enterpriseService.deleteElement(enterprise);
                redirectAttrs.addFlashAttribute("mensaje", "Empresa eliminada con éxito");
                redirectAttrs.addFlashAttribute("clase", "success");
            }
        }
        return "redirect:/enterprises";
    }
}
