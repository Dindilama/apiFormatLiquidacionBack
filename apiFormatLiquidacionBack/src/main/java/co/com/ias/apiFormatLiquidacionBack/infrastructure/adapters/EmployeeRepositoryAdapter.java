package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters;

import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IEmployeeRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IHistorySalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.IEmployeeRepositoryAdapter;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.ISalaryRepositoryAdapter;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
@Transactional
@AllArgsConstructor
public class EmployeeRepositoryAdapter implements IEmployeeRepository {

    private final IEmployeeRepositoryAdapter iEmployeeRepositoryAdapter;
    private final ISalaryRepositoryAdapter iSalaryRepositoryAdapter;
    private final IHistorySalaryRepository iHistorySalaryRepository;


    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeDBO employeeDBO = EmployeeDBO.fromDomain(employee);
        EmployeeDBO employeeSaved = iEmployeeRepositoryAdapter.save(employeeDBO);
        iHistorySalaryRepository.saveHistorySalary(employeeSaved.getSalary(), employeeSaved);
        return EmployeeDBO.toDomain(employeeSaved);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        EmployeeDBO dbo = EmployeeDBO.fromDomain(employee);
        Optional<EmployeeDBO> elementFound = iEmployeeRepositoryAdapter.findById(dbo.getIdEmployee());
        if (elementFound.isEmpty()) {
            throw new NullPointerException("Employee not exist with id: " + employee.getIdEmployee().getClass());
        } else {
            EmployeeDBO employeeSaved = iEmployeeRepositoryAdapter.save(dbo);
            return EmployeeDBO.toDomain(employeeSaved);
        }
    }

    @Override
    public Employee updateEmployeeSalary(Employee employee) {
        EmployeeDBO dbo = EmployeeDBO.fromDomain(employee);
        Optional<EmployeeDBO> elementFound = iEmployeeRepositoryAdapter.findById(dbo.getIdEmployee());
        if (elementFound.isEmpty()) {
            throw new NullPointerException("Employee not exist with id: " + employee.getIdEmployee().getClass());
        } else {
            EmployeeDBO employeeSaved = iEmployeeRepositoryAdapter.save(dbo);
            iHistorySalaryRepository.saveHistorySalary(employeeSaved.getSalary()
                    , employeeSaved);
            return EmployeeDBO.toDomain(employeeSaved);
        }
    }

    @Override
    public Employee findEmployeeById(Long id) {
        Optional<EmployeeDBO> dbo = iEmployeeRepositoryAdapter.findById(id);
        if (dbo.isEmpty()) {
            throw new NullPointerException("Not exist employee with id: " + id);
        } else {
            return EmployeeDBO.toDomain(dbo.get());
        }
    }

    @Override
    public List<Employee> findAllEmployee() {
        return iEmployeeRepositoryAdapter.findAll().stream().map(EmployeeDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        AtomicReference<Boolean> bool = new AtomicReference<>(false);
        Optional<EmployeeDBO> dbo = iEmployeeRepositoryAdapter.findById(id);
        dbo.ifPresent(value -> {
            iHistorySalaryRepository.deleteAllByEmployee(dbo.get());
            iEmployeeRepositoryAdapter.deleteById(id);
            bool.set(true);
        });
        return bool.get();
    }
}

