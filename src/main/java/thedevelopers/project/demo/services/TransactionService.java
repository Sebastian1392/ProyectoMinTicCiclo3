package thedevelopers.project.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Transaction;
import thedevelopers.project.demo.repositories.EmployeeRepository;
import thedevelopers.project.demo.repositories.TransactionRepository;
import thedevelopers.project.demo.util.ServiceTemplate;

import java.util.List;

@Service
public class TransactionService implements ServiceTemplate<Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction createElement(Transaction element) {
        return transactionRepository.save(element);
    }

    @Override
    public Transaction getElement(String id) {
        return transactionRepository.findById(Long.parseLong(id)).orElse(null);
    }

    @Override
    public void deleteElement(Transaction element) {
        transactionRepository.delete(element);
    }

    @Override
    public Transaction saveElement(Transaction element) {
        return transactionRepository.save(element);
    }

    public List<Transaction> getAllEnterpriseMovements(Long idEnterprise){
        return transactionRepository.findAllEnterpriseMovements(idEnterprise);
    }
}
