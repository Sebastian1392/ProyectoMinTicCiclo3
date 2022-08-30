package thedevelopers.project.demo.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idEmployee;

    @Column(name = "name", nullable = false)
    private String nameEmployee;

    @Column(name = "email", unique = true)
    private String emailEmployee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Enterprise enterpriseEmployee;

    @Column(name = "role")
    private RoleName roleName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "createdAt")
    private Date createdAtEmployee;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "updatedAt")
    private Date updatedAtEmployee;


    public Employee(Long idEmployee,String nameEmployee ,String emailEmployee, Enterprise enterpriseEmployee, RoleName roleName, Date createdAtEmployee, Date updatedAtEmployee) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.emailEmployee = emailEmployee;
        this.enterpriseEmployee = enterpriseEmployee;
        this.roleName = roleName;
        this.createdAtEmployee = createdAtEmployee;
        this.updatedAtEmployee = updatedAtEmployee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", nameEmployee='" + nameEmployee + '\'' +
                ", emailEmployee='" + emailEmployee + '\'' +
                ", enterpriseEmployee=" + enterpriseEmployee +
                ", roleName=" + roleName +
                ", createdAtEmployee=" + createdAtEmployee +
                ", updatedAtEmployee=" + updatedAtEmployee +
                '}';
    }
}
