package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters;

import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.liquidacion.Liquidacion;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.ILiquidacionRepository;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.ILiquidacionRepositoryAdapter;

import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.HistorySalaryDBO;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.LiquidacionDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class LiquidacionRepositoryAdapter implements ILiquidacionRepository {

    private final ILiquidacionRepositoryAdapter iLiquidacionRepositoryAdapter;
    private final IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;

    @Override
    public Liquidacion saveLiquidacion(Liquidacion liquidacion, Employee employee) {
        LiquidacionDBO liquidacionDBO = LiquidacionDBO.fromDomain(liquidacion);
        LiquidacionDBO liquidacionSaved = iLiquidacionRepositoryAdapter.save(liquidacionDBO);
        employee.setStatus(liquidacion.getMotivo());
        iEmployeeRepositoryAdapter.save(EmployeeDBO.fromDomain(employee));
        return LiquidacionDBO.toDomain(liquidacionSaved);
    }

    @Override
    public Liquidacion updateLiquidacion(Liquidacion liquidacion) {
        LiquidacionDBO dbo = LiquidacionDBO.fromDomain(liquidacion);
        Optional<LiquidacionDBO> elementFound = iLiquidacionRepositoryAdapter.findById(dbo.getId());
        if (elementFound.isEmpty()) {
            throw new NullPointerException("Liquidacion not exist with this id: " + liquidacion.getEmployee().getClass());

        } else {
            LiquidacionDBO liquidacionSaved = iLiquidacionRepositoryAdapter.save(dbo);
            return LiquidacionDBO.toDomain(liquidacionSaved);
        }
    }

    @Override
    public List<Liquidacion> findAllLiquidacion() {
        return iLiquidacionRepositoryAdapter.findAll().stream().map(LiquidacionDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteLiquidacion(Long liquidacion) {
        AtomicReference<Boolean> bool = new AtomicReference<>(false);
        Optional<LiquidacionDBO> dbo = iLiquidacionRepositoryAdapter.findById(liquidacion);
        dbo.ifPresent(value -> {
            iLiquidacionRepositoryAdapter.deleteById(liquidacion);
            bool.set(true);
        });
        return bool.get();
    }

    @Override
    public Liquidacion findLiquidacionById(Long liquidacion) {
        Optional<LiquidacionDBO> dbo = iLiquidacionRepositoryAdapter.findById(liquidacion);
        if (dbo.isEmpty()) {
            throw new NullPointerException("Not exist liquidacion with id: " + liquidacion);
        } else {
            return LiquidacionDBO.toDomain(dbo.get());
        }
    }

    @Override
    public List<Liquidacion> findHistoryByEmployee(EmployeeDBO employeeDBO) {
        return iLiquidacionRepositoryAdapter.findByEmployeeDBO(employeeDBO).stream().map(LiquidacionDBO::toDomain).
                collect(Collectors.toList());
    }
}
