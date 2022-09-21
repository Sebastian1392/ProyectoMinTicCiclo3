package thedevelopers.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.Transaction;

import java.util.List;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM transaction WHERE id_enterprise = ?1")
    List<String> findEnterpriseTransactions(Long idEnterprise);

    @Query(nativeQuery = true,value = "SELECT * FROM employee WHERE id_enterprise = ?1")
    List<String> findEnterpriseEmployees(Long idEnterprise);

    @Query(nativeQuery = true,value = "SELECT name FROM enterprise WHERE name LIKE ?1")
    String findName(String name);

    @Query(nativeQuery = true,value = "SELECT document FROM enterprise WHERE document LIKE ?1")
    String findDocument(String document);
}
