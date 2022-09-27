package thedevelopers.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thedevelopers.project.demo.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByEmailEmployee(String email);

    @Query(nativeQuery = true,value = "SELECT * FROM transaction WHERE id_employee = ?1")
    List<String> findEmployeeTransactions(Long idEmployee);

    @Query(nativeQuery = true,value = "SELECT email FROM employee WHERE email LIKE ?1")
    String findEmail(String email);
}
