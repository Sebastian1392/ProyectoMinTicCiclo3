package thedevelopers.project.demo.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

//@Entity
@Data
@Table(name = "Enterprise")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idEnterprise;

    @Column(name = "name", unique = true)
    private String nameEnterprise;

    @Column(name = "document", unique = true)
    private String documentEnterprise;

    @Column(name = "phone")
    private String phoneEnterprise;

    @Column(name = "address")
    private String addressEnterprise;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "createdAt")
    private Date createdAtEnterprise;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "updatedAt")
    private Date updatedAtEnterprise;

    private ArrayList<Employee> employees;
    private ArrayList<Transaction> transactionsEnterprise;
    public Enterprise(Long idEnterprise, String nameEnterprise, String documentEnterprise, String phoneEnterprise, String addressEnterprise, Date createdAtEnterprise, Date updatedAtEnterprise) {
        this.idEnterprise = idEnterprise;
        this.nameEnterprise = nameEnterprise;
        this.documentEnterprise = documentEnterprise;
        this.phoneEnterprise = phoneEnterprise;
        this.addressEnterprise = addressEnterprise;
        this.createdAtEnterprise = createdAtEnterprise;
        this.updatedAtEnterprise = updatedAtEnterprise;
        employees = new ArrayList<>();
        transactionsEnterprise = new ArrayList<>();
    }
}
