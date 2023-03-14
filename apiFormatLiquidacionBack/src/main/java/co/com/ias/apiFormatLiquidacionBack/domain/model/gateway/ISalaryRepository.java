package co.com.ias.apiFormatLiquidacionBack.domain.model.gateway;

import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;

import java.util.List;

public interface ISalaryRepository {
    Salary findSalaryById(Long salary);

    Salary saveSalary(Salary salary);

    Salary updateSalary(Salary salary);

    List<Salary> findAllSalary();

    Boolean deleteSalary(Long salary);
}
