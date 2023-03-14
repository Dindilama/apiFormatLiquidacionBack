package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo;

import co.com.ias.apiFormatLiquidacionBack.domain.model.historysalary.HistorySalary;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HistorySalary")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorySalaryDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee")
    private EmployeeDBO employeeDBO;

    @ManyToOne
    @JoinColumn(name = "salary")
    private SalaryDBO salaryDBO;

    public Long getId() {
        return id;
    }

    public EmployeeDBO getEmployeeDBO() {
        return employeeDBO;
    }

    public SalaryDBO getSalaryDBO() {
        return salaryDBO;
    }

    public HistorySalaryDBO(SalaryDBO salaryDBO, EmployeeDBO employeeDBO) {
        this.employeeDBO = employeeDBO;
        this.salaryDBO = salaryDBO;
    }

    public static HistorySalary toDomain(HistorySalaryDBO historySalaryDBO) {
        return new HistorySalary(historySalaryDBO.getId(),
                SalaryDBO.toDomain(historySalaryDBO.getSalaryDBO()),
                EmployeeDBO.toDomain(historySalaryDBO.getEmployeeDBO())
        );
    }

    public static HistorySalaryDBO fromDomain(HistorySalary historySalary) {
        return new HistorySalaryDBO(
                SalaryDBO.fromDomain(historySalary.getSalary()), EmployeeDBO.fromDomain(historySalary.getEmployee())
        );
    }
}

