package co.com.ias.apiFormatLiquidacionBack.domain.model.gateway;

import co.com.ias.apiFormatLiquidacionBack.domain.model.historysalary.HistorySalary;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.SalaryDBO;

import java.util.List;

public interface IHistorySalaryRepository {

    HistorySalary saveHistorySalary(SalaryDBO salary, EmployeeDBO employee);

    List<HistorySalary> findHistoryByEmployee(EmployeeDBO employeeDBO);

    void deleteAllByEmployee(EmployeeDBO employeeDBO);

}
