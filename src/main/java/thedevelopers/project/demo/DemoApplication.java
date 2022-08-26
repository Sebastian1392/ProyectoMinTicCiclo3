package thedevelopers.project.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Enterprise;
import thedevelopers.project.demo.domain.RoleName;

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
		Employee employee1 = new Employee((long) 3, "manuel@gmail.com", enterprise1, RoleName.ADMIN, new Date(), new Date());
		Employee employee2 = new Employee((long) 4, "sebastian@gmail.com", enterprise1, RoleName.ADMIN, new Date(), new Date());
		Employee employee3 = new Employee((long) 3, "pepito@gmail.com", enterprise2, RoleName.OPERARIO, new Date(), new Date());
		enterprise1.addEmployee(employee1);
		enterprise1.addEmployee(employee2);
		enterprise2.addEmployee(employee3);

		System.out.println("Empresa: " + enterprise1.getNameEnterprise());
		System.out.println("Empleados");
		for (Employee e:
			 enterprise1.getEmployees()) {
			System.out.println("Codigo: " + e.getIdEmployee() +
					"\nCorreo: " + e.getEmailEmployee());
		}

		System.out.println("\n\n\nEmpresa: " + enterprise2.getNameEnterprise());
		System.out.println("Empleados");
		for (Employee e:
				enterprise2.getEmployees()) {
			System.out.println("Codigo: " + e.getIdEmployee() +
					"\nCorreo: " + e.getEmailEmployee());
		}
	}


	
}
