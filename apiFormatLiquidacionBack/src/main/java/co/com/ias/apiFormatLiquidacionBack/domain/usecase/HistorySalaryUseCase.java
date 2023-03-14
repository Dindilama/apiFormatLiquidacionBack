package co.com.ias.apiFormatLiquidacionBack.domain.usecase;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.HistorySalaryDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IEmployeeRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IHistorySalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.historysalary.HistorySalary;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;

import java.util.List;
import java.util.stream.Collectors;

public class HistorySalaryUseCase {

    private final IHistorySalaryRepository iHistorySalaryRepository;
    private final IEmployeeRepository iEmployeeRepository;


    public HistorySalaryUseCase(IHistorySalaryRepository iHistorySalaryRepository,
                                IEmployeeRepository iEmployeeRepository) {
        this.iHistorySalaryRepository = iHistorySalaryRepository;
        this.iEmployeeRepository = iEmployeeRepository;
    }

    public List<HistorySalaryDTO> findSalaryById(Long id) {
        Employee employeeById = this.iEmployeeRepository.findEmployeeById(id);
        EmployeeDBO employeeDBO = EmployeeDBO.fromDomain(employeeById);
        List<HistorySalary> historyByEmployee = this.iHistorySalaryRepository.findHistoryByEmployee(employeeDBO);
        return historyByEmployee.stream().map(HistorySalaryDTO::fromDomain).collect(Collectors.toList());
    }


}
