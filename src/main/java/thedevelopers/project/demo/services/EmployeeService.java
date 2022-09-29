package thedevelopers.project.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.RoleName;
import thedevelopers.project.demo.repositories.EmployeeRepository;
import thedevelopers.project.demo.util.ServiceTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements ServiceTemplate<Employee> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeByEmail(String email){
        Employee employee = this.employeeRepository.findEmployeeByEmailEmployee(email);
        return employee;
    }

    public Employee getEmployee(Map<String, Object> userData){
        String email = userData.get("email").toString();
        return findEmployeeByEmail(email);
    }

    @Override
    public Employee createElement(Employee element) {
        element.setCreatedAtEmployee(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        element.setUpdatedAtEmployee(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return employeeRepository.save(element);
    }

    @Override
    public Employee getElement(String id) {
        return employeeRepository.findById(Long.parseLong(id)).orElse(null);
    }

    @Override
    public void deleteElement(Employee element) {
        employeeRepository.delete(element);
    }

    @Override
    public Employee updateElement(Employee element, Employee newElement) {
        element.setNameEmployee((String) validateData(element.getNameEmployee(),newElement.getNameEmployee()));
        element.setEmailEmployee((String) validateData(element.getEmailEmployee(),newElement.getEmailEmployee()));
        element.setRoleName((RoleName) validateData(element.getRoleName(),newElement.getRoleName()));
        element.setUpdatedAtEmployee(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return employeeRepository.save(element);
    }

    private Object validateData(Object dataElement, Object dataNewElement){
        if(dataNewElement != null){
            return  dataNewElement;
        }
        return dataElement;
    }

    public boolean haveTransactions(String idEmployee){
        return employeeRepository.findEmployeeTransactions(Long.parseLong(idEmployee)).size() > 0;
    }

    public boolean findEmail(String email){
        return employeeRepository.findEmail(email) != null;
    }
}
