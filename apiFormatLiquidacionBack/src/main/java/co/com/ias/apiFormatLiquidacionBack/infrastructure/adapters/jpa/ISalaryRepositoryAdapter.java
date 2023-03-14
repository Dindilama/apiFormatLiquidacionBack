package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa;

import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.SalaryDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalaryRepositoryAdapter extends JpaRepository<SalaryDBO, Long> {

}
