package thedevelopers.project.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Transaction;
import thedevelopers.project.demo.repositories.EmployeeRepository;
import thedevelopers.project.demo.repositories.TransactionRepository;
import thedevelopers.project.demo.util.ServiceTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
        element.setCreatedAtTransaction(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        element.setUpdatedAtTransaction(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
    public Transaction updateElement(Transaction element, Transaction newElement) {
        element.setConceptTransaction((String)validateData(element.getConceptTransaction(),newElement.getConceptTransaction()));
        element.setAmountTransaction((Float)validateData(element.getAmountTransaction(),newElement.getAmountTransaction()));
        element.setUpdatedAtTransaction(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return transactionRepository.save(element);
    }
    private Object validateData(Object dataElement, Object dataNewElement){
        if(dataNewElement != null){
            return  dataNewElement;
        }
        return dataElement;
    }

    public List<Transaction> getAllEnterpriseMovements(Long idEnterprise){
        return transactionRepository.findAllEnterpriseMovements(idEnterprise);
    }

    public Float getTotalAmount(List<Transaction> enterpriseTransactions){
        Float totalAmount = Float.valueOf(0);
        for (Transaction transaction: enterpriseTransactions) {
            totalAmount += transaction.getAmountTransaction();
        }
        return totalAmount;
    }
}
