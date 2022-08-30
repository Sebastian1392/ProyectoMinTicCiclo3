package thedevelopers.project.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.RoleName;
import thedevelopers.project.demo.domain.Transaction;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		Enterprise enterprise1 = new Enterprise((long)1,"IPhone","1826181","3223791256","Calle 16 Bis # 50-12",null,null);
		Enterprise enterprise2 = new Enterprise((long)2,"Samsung","1596328","3115006697","Calle 14 # 12-50",null,null);
		Employee employee1 = new Employee((long) 3, "Manuel Chaparro","manuel@gmail.com", enterprise1, RoleName.ADMIN, new Date(), new Date());
		Employee employee2 = new Employee((long) 4, "Sebastian Sanchez","sebastian@gmail.com", enterprise1, RoleName.ADMIN, new Date(), new Date());
		Employee employee3 = new Employee((long) 3, "Pepito Perez","pepito@gmail.com", enterprise2, RoleName.OPERARIO, new Date(), new Date());
        System.out.println(enterprise1.toString());
        System.out.println(enterprise2.toString());
        enterprise1.addEmployee(employee1);
		enterprise1.addEmployee(employee2);
		enterprise2.addEmployee(employee3);
        enterprise1.setNameEnterprise("Google");
        enterprise2.setAddressEnterprise("Calle 15 # 25 - 76");
        enterprise1.setPhoneEnterprise("3112412731");
        enterprise1.setDocumentEnterprise("1234686");
        System.out.println(enterprise1.toString());
        System.out.println(enterprise2.toString());

		System.out.println("Empresa: " + enterprise1.getNameEnterprise());
		System.out.println("Empleados");
		for (Employee e: enterprise1.getEmployees()) {
			System.out.println(e.toString());
		}

		System.out.println("\n\n\nEmpresa: " + enterprise2.getNameEnterprise());
		System.out.println("Empleados");
		for (Employee e: enterprise2.getEmployees()) {
			System.out.println(e.toString());
		}
		employee1.setNameEmployee("Santiago Hernandez");
		employee2.setNameEmployee("Felipe Hurtado");
		employee3.setEmailEmployee("pepe@hotmail.com");
		employee1.setEnterpriseEmployee(enterprise2);
		enterprise1.removeEmployee(employee1.getIdEmployee());
		enterprise2.addEmployee(employee1);
		employee2.setRoleName(RoleName.OPERARIO);

		System.out.println("Empresa: " + enterprise1.getNameEnterprise());
		System.out.println("Empleados");
		for (Employee e: enterprise1.getEmployees()) {
			System.out.println(e.toString());
		}

		System.out.println("\n\n\nEmpresa: " + enterprise2.getNameEnterprise());
		System.out.println("Empleados");
		for (Employee e: enterprise2.getEmployees()) {
			System.out.println(e.toString());
		}

		Transaction transaction1 = new Transaction((long)1233,"Deposito bancario",(float)752305,enterprise1.getEmployees().get(0),enterprise1,null,null);
		System.out.println("\n\n"+transaction1.toString());
		transaction1.setAmountTransaction((float)-5976.89);
		transaction1.setConceptTransaction("Deposito en cuenta de ahorros");
		System.out.println(transaction1.toString());
	}

}
