package thedevelopers.project.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.repositories.EnterpriseRepository;
import thedevelopers.project.demo.util.ServiceTemplate;

import java.util.List;

@Service
public class EnterpriseService implements ServiceTemplate<Enterprise> {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public List<Enterprise> getAll() {
        return enterpriseRepository.findAll();
    }

    @Override
    public Enterprise createElement(Enterprise element) {
        return enterpriseRepository.save(element);
    }

    @Override
    public Enterprise getElement(String id) {
        return enterpriseRepository.findById(Long.parseLong(id)).orElse(null);
    }

    @Override
    public void deleteElement(Enterprise element) {
        enterpriseRepository.delete(element);
    }

    @Override
    public Enterprise saveElement(Enterprise element) {
        return enterpriseRepository.save(element);
    }
}
