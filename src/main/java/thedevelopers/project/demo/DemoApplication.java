package thedevelopers.project.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thedevelopers.project.demo.domain.Employee;
import thedevelopers.project.demo.domain.Enterprise;

@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		Enterprise enterprise1 = new Enterprise((long)1,"IPhone","1826181","3223791256","Calle 16 Bis # 50-12",null,null);
		Enterprise enterprise2 = new Enterprise((long)2,"Samsung","1596328","3115006697","Calle 14 # 12-50",null,null);
		System.out.println(enterprise1.getNameEnterprise());
		System.out.println(enterprise2.getNameEnterprise());
		enterprise1.setNameEnterprise("Xiaomi");
		System.out.println(enterprise1.getNameEnterprise());
	}


	
}
