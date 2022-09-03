package thedevelopers.project.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.repositories.EmployeeRepository;
import thedevelopers.project.demo.util.ServiceTemplate;

import java.util.List;

@Service
public class EmployeeService implements ServiceTemplate<Employee> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createElement(Employee element) {
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
    public Employee saveElement(Employee element) {
        return employeeRepository.save(element);
    }
}
