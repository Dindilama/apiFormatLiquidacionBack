package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa;

import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.LiquidacionDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILiquidacionRepositoryAdapter extends JpaRepository<LiquidacionDBO, Long> {

    List<LiquidacionDBO> findByEmployeeDBO(EmployeeDBO employeeDBO);
}
