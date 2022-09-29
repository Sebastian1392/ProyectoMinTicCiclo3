package thedevelopers.project.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.repositories.EnterpriseRepository;
import thedevelopers.project.demo.util.ServiceTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
        element.setCreatedAtEnterprise(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        element.setUpdatedAtEnterprise(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
    public Enterprise updateElement(Enterprise element, Enterprise newElement){
        element.setNameEnterprise(validateData(element.getNameEnterprise(),newElement.getNameEnterprise()));
        element.setDocumentEnterprise(validateData(element.getDocumentEnterprise(),newElement.getDocumentEnterprise()));
        element.setPhoneEnterprise(validateData(element.getPhoneEnterprise(),newElement.getPhoneEnterprise()));
        element.setAddressEnterprise(validateData(element.getAddressEnterprise(),newElement.getAddressEnterprise()));
        element.setUpdatedAtEnterprise(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return enterpriseRepository.save(element);
    }

    public String validateData(String dataElement, String dataNewElement){
        if(dataNewElement != null){
            return  dataNewElement;
        }
        return dataElement;
    }

    public boolean haveAsociations(String idEnterprise){
        Long id = Long.parseLong(idEnterprise);
        return enterpriseRepository.findEnterpriseTransactions(id).size() > 0 || enterpriseRepository.findEnterpriseEmployees(id).size() > 0;
    }

    public String findDocumentAndName(String name, String document){
        String message = "";
        if(enterpriseRepository.findName(name) != null){
            message = "nombre";
        }else if (enterpriseRepository.findDocument(document) != null) {
            message = "documento";
        }
        return message;
    }

    public String validateData(Enterprise element, Enterprise newElement){
        String message = "";
        if(!element.getNameEnterprise().equals(newElement.getNameEnterprise()) && element.getDocumentEnterprise().equals(newElement.getDocumentEnterprise())){
            message = findDocumentAndName(newElement.getNameEnterprise(),"");
        }else if(element.getNameEnterprise().equals(newElement.getNameEnterprise()) && !element.getDocumentEnterprise().equals(newElement.getDocumentEnterprise())){
            message = findDocumentAndName("",newElement.getDocumentEnterprise());
        }else if(!element.getNameEnterprise().equals(newElement.getNameEnterprise()) && !element.getDocumentEnterprise().equals(newElement.getDocumentEnterprise())){
            message = findDocumentAndName(newElement.getNameEnterprise(),newElement.getDocumentEnterprise());
        }
        return message;
    }
}
