package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters;

import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IHistorySalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.historysalary.HistorySalary;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.IHistorySalaryRepositoryAdapter;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.HistorySalaryDBO;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.SalaryDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class HistorySalaryRepositoryAdapter implements IHistorySalaryRepository {

    private final IHistorySalaryRepositoryAdapter iHistorySalaryRepositoryAdapter;


    @Override
    public HistorySalary saveHistorySalary(SalaryDBO salary, EmployeeDBO employee) {
        HistorySalary historySalary = new HistorySalary(SalaryDBO.toDomain(salary), EmployeeDBO.toDomain(employee));
        HistorySalaryDBO historySalaryDBO = HistorySalaryDBO.fromDomain(historySalary);
        HistorySalaryDBO salarySaved = iHistorySalaryRepositoryAdapter.save(historySalaryDBO);
        return HistorySalaryDBO.toDomain(salarySaved);
    }

    @Override
    public List<HistorySalary> findHistoryByEmployee(EmployeeDBO employeeDBO) {
        return iHistorySalaryRepositoryAdapter.findByEmployeeDBO(employeeDBO).stream().map(HistorySalaryDBO::toDomain).
                collect(Collectors.toList());
    }

    @Override
    public void deleteAllByEmployee(EmployeeDBO employeeDBO){
        iHistorySalaryRepositoryAdapter.deleteAllByEmployeeDBO(employeeDBO);
    }
}

