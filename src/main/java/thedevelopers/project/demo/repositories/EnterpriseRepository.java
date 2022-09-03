package thedevelopers.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thedevelopers.project.demo.domain.Enterprise;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
