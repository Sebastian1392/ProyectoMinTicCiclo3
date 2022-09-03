package thedevelopers.project.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "Employee")
public class Employee implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idEmployee;

    @Column(name = "name", nullable = false)
    private String nameEmployee;

    @Column(name = "email", unique = true)
    private String emailEmployee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_enterprise", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
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

    public Employee(String nameEmployee, String emailEmployee, Enterprise enterpriseEmployee, RoleName roleName, Date createdAtEmployee, Date updatedAtEmployee) {
        this.nameEmployee = nameEmployee;
        this.emailEmployee = emailEmployee;
        this.enterpriseEmployee = enterpriseEmployee;
        this.roleName = roleName;
        this.createdAtEmployee = createdAtEmployee;
        this.updatedAtEmployee = updatedAtEmployee;
    }

    public Employee(){


    }
}
