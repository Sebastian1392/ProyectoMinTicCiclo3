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
		Enterprise enterprise1 = new Enterprise((long)1,"Colombina","4030434","31123434343","Calle 35",null,null);
		System.out.println(enterprise1.getNameEnterprise());
		enterprise1.setNameEnterprise("Postobon");
		System.out.println(enterprise1.getNameEnterprise());
	}


	
}
