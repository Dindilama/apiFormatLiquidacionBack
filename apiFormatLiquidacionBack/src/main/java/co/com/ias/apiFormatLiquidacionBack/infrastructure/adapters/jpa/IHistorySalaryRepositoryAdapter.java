package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa;

import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.HistorySalaryDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHistorySalaryRepositoryAdapter extends JpaRepository<HistorySalaryDBO, Long> {

    List<HistorySalaryDBO> findByEmployeeDBO(EmployeeDBO employeeDBO);

    void deleteAllByEmployeeDBO(EmployeeDBO employeeDBO);
}
