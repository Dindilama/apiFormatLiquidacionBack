package co.com.ias.apiFormatLiquidacionBack.domain.model.dto;

import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.*;
import co.com.ias.apiFormatLiquidacionBack.domain.model.historysalary.HistorySalary;
import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HistorySalaryDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public SalaryDTO getSalary() {
        return salary;
    }

    public void setSalary(SalaryDTO salary) {
        this.salary = salary;
    }

    private EmployeeDTO employee;
    private SalaryDTO salary;


    public HistorySalaryDTO(Long id, EmployeeDTO employee, SalaryDTO salary) {
        this.id = id;
        this.employee = employee;
        this.salary = salary;
    }

    public static HistorySalaryDTO fromDomain(HistorySalary historySalary) {
        return new HistorySalaryDTO(historySalary.getId(),
                EmployeeDTO.fromDomain(historySalary.getEmployee()),
                SalaryDTO.fromDomain(historySalary.getSalary()));
    }

    public Employee toDomain(EmployeeDTO employeeDTO, Salary salaryDTO) {
        return new Employee(employeeDTO.getId(),
                new Document(employeeDTO.getDocument()),
                new Job(employeeDTO.getJob()),
                new Name(employeeDTO.getName()),
                new StartDate(employeeDTO.getStartDate()), salaryDTO, employeeDTO.getStatus()
        );
    }
}
