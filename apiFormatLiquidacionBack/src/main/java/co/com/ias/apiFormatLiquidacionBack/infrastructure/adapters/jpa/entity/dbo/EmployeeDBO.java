package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo;

import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idEmployee;
    private String document;
    private String name;
    private LocalDate startDate;
    private String job;
    @ManyToOne
    @JoinColumn(name = "salary")
    private SalaryDBO salary;
    private String status;


    public Long getIdEmployee() {
        return idEmployee;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public EmployeeDBO(Long idEmployee, String document, String name, LocalDate startDate, String job) {
        this.idEmployee = idEmployee;
        this.document = document;
        this.name = name;
        this.startDate = startDate;
        this.job = job;
    }

    public static Employee toDomain(EmployeeDBO employeeDBO) {
        return new Employee(employeeDBO.getIdEmployee(),
                new Document(employeeDBO.getDocument()),
                new Job(employeeDBO.getJob()),
                new Name(employeeDBO.getName()),
                new StartDate(employeeDBO.getStartDate()),
                SalaryDBO.toDomain(employeeDBO.getSalary()),
                employeeDBO.getStatus());
    }

    public static EmployeeDBO fromDomain(Employee employee) {
        return new EmployeeDBO(employee.getIdEmployee(),
                employee.getDocument().getValue(),
                employee.getName().getValue(),
                employee.getStartDate().getValue(),
                employee.getJob().getValue(),
                SalaryDBO.fromDomain(employee.getSalary()),
                employee.getStatus());
    }
}


