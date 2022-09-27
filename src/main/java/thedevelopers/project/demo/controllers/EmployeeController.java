package thedevelopers.project.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.RoleName;
import thedevelopers.project.demo.services.EmployeeService;
import thedevelopers.project.demo.services.EnterpriseService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
//@RestController
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal!=null){
            Employee employee = employeeService.getEmployee(principal.getClaims());
            List<Enterprise> enterprises =this.enterpriseService.getAll();
            if (employee != null){
                model.addAttribute("employee", employee);
                return "index";
            }else{
                model.addAttribute("employee", new Employee(principal.getEmail().toString()));
                model.addAttribute("enterprises", enterprises);
                model.addAttribute("rolAdmin", RoleName.ADMIN);
                model.addAttribute("rolOper", RoleName.OPERARIO);
            }
        }
        return "login";
    }

    @GetMapping("/users")
    public String getEmployeeList(Model model, @AuthenticationPrincipal OidcUser principal){
        model.addAttribute("userList", employeeService.getAll());
        boolean isAdmin = employeeService.getEmployee(principal.getClaims()).getRoleName().getTextName().equalsIgnoreCase("ADMIN");
        model.addAttribute("isAdmin", isAdmin);
        return "users";
    }

    @GetMapping("/new_user")
    public String createEmployee(Model model, Employee employee){
        log.info(employee + "");
        if(employee.getNameEmployee() != null){
            model.addAttribute("mensaje", "El correo que intenta registrar ya existe");
            model.addAttribute("employeeData", employee);
        }
        model.addAttribute("roles", RoleName.values());
        model.addAttribute("enterpriseList",enterpriseService.getAll());
        return "new-user";
    }

    @PostMapping("/users")
    public String saveNewEnterprise(@Valid Employee employee,RedirectAttributes redirectAttrs, Model model){
        if(!employeeService.findEmail(employee.getEmailEmployee())){
            employeeService.createElement(employee);
            redirectAttrs.addFlashAttribute("mensaje", "Usuario creado con éxito");
            redirectAttrs.addFlashAttribute("clase", "success");
        }else{
            return this.createEmployee(model, employee);
        }
        return "redirect:/users";
    }

    @PostMapping("/login")
    public RedirectView createNewUser(Employee employee, Model model){
        model.addAttribute(employee);
        employeeService.createElement(employee);
        return new RedirectView("/index");
    }



    @GetMapping("/user/{id}")
    public Employee getEmployee(@PathVariable String id){
        return employeeService.getElement(id);
    }

    @GetMapping("/update_user/{id}")
    public String updateEmployee(@PathVariable String id, Model model, Employee newEmployee){
        Employee employee = employeeService.getElement(id);
        if(newEmployee.getNameEmployee() != null){
            model.addAttribute("mensaje", "El correo que intenta registrar ya existe");
        }
        model.addAttribute("employeeData", employee);
        model.addAttribute("roles", RoleName.values());
        model.addAttribute("enterpriseList",enterpriseService.getAll());
        return "update-user";
    }

    @PatchMapping("/user/{id}")
    public String saveChangesEmployee(@Valid Employee employee,@PathVariable String id, Model model, RedirectAttributes redirectAttrs){
        Employee employeeFound = employeeService.getElement(id);
        if(employeeService.findEmail(employee.getEmailEmployee()) && !employee.getEmailEmployee().equals(employeeFound.getEmailEmployee())){
            return this.updateEmployee(id, model, employee);
        }else{
            if(employeeFound != null){
                Enterprise newEnterprise = employee.getEnterpriseEmployee();
                if(newEnterprise != null && enterpriseService.getElement(String.valueOf(newEnterprise.getIdEnterprise())) != null){
                    employeeFound.setEnterpriseEmployee(enterpriseService.getElement(String.valueOf(newEnterprise.getIdEnterprise())));
                }
                employeeService.updateElement(employeeFound, employee);
                redirectAttrs.addFlashAttribute("mensaje", "Usuario actualizado con éxito");
                redirectAttrs.addFlashAttribute("clase", "success");
            }
        }
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public String deleteEmployee(@PathVariable String id, RedirectAttributes redirectAttrs){
        if(employeeService.haveTransactions(id)){
            redirectAttrs.addFlashAttribute("mensaje", "El usuario no se puede eliminar \n porque tiene transacciones asociadas");
        }else{
            Employee employee = employeeService.getElement(id);
            if (employeeService.getElement(id) != null){
                employee.setEnterpriseEmployee(null);
                employeeService.deleteElement(employee);
                redirectAttrs.addFlashAttribute("mensaje", "Usuario eliminado con éxito");
                redirectAttrs.addFlashAttribute("clase", "success");
            }
        }
        return "redirect:/users";
    }

}
