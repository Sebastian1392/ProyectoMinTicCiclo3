package thedevelopers.project.demo.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idTransaction;

    @Column(name = "concept")
    private String conceptTransaction;

    @Column(name = "amount")
    private Float amountTransaction;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Employee employeeTransaction;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_enterprise", referencedColumnName = "id")
    private Enterprise enterpriseTransaction;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "createdAt")
    private Date createdAtTransaction;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "updatedAt")
    private Date updatedAtTransaction;

    public Transaction(Long idTransaction, String conceptTransaction, Float amountTransaction, Employee employeeTransaction, Enterprise enterpriseTransaction, Date createdAtTransaction, Date updatedAtTransaction) {
        this.idTransaction = idTransaction;
        this.conceptTransaction = conceptTransaction;
        this.amountTransaction = amountTransaction;
        this.employeeTransaction = employeeTransaction;
        this.enterpriseTransaction = enterpriseTransaction;
        this.createdAtTransaction = createdAtTransaction;
        this.updatedAtTransaction = updatedAtTransaction;
    }
}
