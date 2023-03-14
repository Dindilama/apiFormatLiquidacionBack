package co.com.ias.apiFormatLiquidacionBack.domain.model.gateway;

import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.liquidacion.Liquidacion;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;

import java.util.List;

public interface ILiquidacionRepository {

    Liquidacion saveLiquidacion(Liquidacion liquidacion, Employee employee);

    abstract Liquidacion updateLiquidacion(Liquidacion liquidacion);

    List<Liquidacion> findAllLiquidacion();

    Boolean deleteLiquidacion(Long liquidacion);

    Liquidacion findLiquidacionById(Long liquidacion);

    List<Liquidacion> findHistoryByEmployee(EmployeeDBO employeeDBO);


}
