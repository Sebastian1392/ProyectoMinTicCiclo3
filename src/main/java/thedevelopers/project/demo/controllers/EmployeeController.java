package thedevelopers.project.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.RoleName;
import thedevelopers.project.demo.services.EmployeeService;
import thedevelopers.project.demo.services.EnterpriseService;

import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/users")
    public List<Employee> getEmployeeList(){
         return employeeService.getAll();
    }

    @PostMapping("/users")
    public Employee createEmployee(@RequestBody Employee employee){
        employee.setEnterpriseEmployee(enterpriseService.getElement(String.valueOf(employee.getEnterpriseEmployee().getIdEnterprise())));
        return employeeService.createElement(employee);
    }

    @GetMapping("/user/{id}")
    public Employee getEmployee(@PathVariable String id){
        return employeeService.getElement(id);
    }

    @PatchMapping("/user/{id}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable String id){
        Employee employeeFound = employeeService.getElement(id);
        if(employeeFound != null){
            Enterprise newEnterprise = employee.getEnterpriseEmployee();
            if(newEnterprise != null && enterpriseService.getElement(String.valueOf(newEnterprise.getIdEnterprise())) != null){
                employeeFound.setEnterpriseEmployee(enterpriseService.getElement(String.valueOf(newEnterprise.getIdEnterprise())));
            }
            return employeeService.updateElement(employeeFound,employee);
        }
        return null;
    }

    @DeleteMapping("/user/{id}")
    public String deleteEmployee(@PathVariable String id){
        Employee employee = employeeService.getElement(id);
        if (employeeService.getElement(id) != null){
            employeeService.deleteElement(employee);
            return "Empresa eliminada con exito";
        }
        return "No se pudo eliminar el elemento, no existe";
    }

}
