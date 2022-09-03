package thedevelopers.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thedevelopers.project.demo.domain.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM transaction WHERE id_enterprise = ?1")
    List<Transaction> findAllEnterpriseMovements(Long idEnterprise);
}
