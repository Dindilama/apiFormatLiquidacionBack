package co.com.ias.apiFormatLiquidacionBack.domain.model.historysalary;

import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HistorySalary {
    public Salary getSalary() {
        return salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    private Salary salary;
    private Employee employee;

    public Long getId() {
        return id;
    }

    private Long id;


    public HistorySalary(Salary salary, Employee employee) {
        this.salary = salary;
        this.employee = employee;
    }

    public HistorySalary(Long id, Salary salary, Employee employee) {
        this.id = id;
        this.salary = salary;
        this.employee = employee;
    }


}
