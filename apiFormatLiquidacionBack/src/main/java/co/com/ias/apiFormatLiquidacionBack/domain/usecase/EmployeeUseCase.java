package co.com.ias.apiFormatLiquidacionBack.domain.usecase;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.EmployeeDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IEmployeeRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.ISalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeUseCase {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/dd/MM");
    private final IEmployeeRepository iEmployeeRepository;
    private final ISalaryRepository iSalaryRepository;

    public EmployeeUseCase(IEmployeeRepository iEmployeeRepository, ISalaryRepository iSalaryRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
        this.iSalaryRepository = iSalaryRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setStatus("Activo");
        Salary salary = new Salary(employeeDTO.getSalary().getSalary());
        salary.setFechaModificacion(LocalDate.parse(LocalDate.now().format(formatter), formatter));
        salary = this.iSalaryRepository.saveSalary(salary);
        Employee employee = employeeDTO.toDomain(employeeDTO, salary);
        return EmployeeDTO.fromDomain(this.iEmployeeRepository.saveEmployee(employee));
    }

    public EmployeeDTO findEmployeeById(Long id) {
        Employee employee = this.iEmployeeRepository.findEmployeeById(id);
        if (employee != null) {
            return EmployeeDTO.fromDomain(employee);
        }
        return null;
    }

    public ArrayList<EmployeeDTO> findAllEmployees() {
        List<Employee> employee = this.iEmployeeRepository.findAllEmployee();
        return (ArrayList<EmployeeDTO>) employee.stream().map(EmployeeDTO::fromDomain).collect(Collectors.toList());
    }


    public Boolean deleteEmployee(Long id) {
        return iEmployeeRepository.deleteEmployee(id);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employeeDb = this.iEmployeeRepository.findEmployeeById(employeeDTO.getId());
        Employee employee = employeeDTO.toDomain(employeeDTO, employeeDTO.getSalary());

        if (Objects.equals(employee.getSalary().getSalary(), employeeDb.getSalary().getSalary())) {
            Salary salary = employeeDb.getSalary();
            employee.setSalary(salary);
            return EmployeeDTO.fromDomain(iEmployeeRepository.updateEmployee(employee));
        } else if (employee.getSalary().getSalary() == 0) {
            throw new IllegalArgumentException("The new salary cannot be empty");
        } else if (employee.getSalary().getSalary() < employeeDb.getSalary().getSalary()) {
            throw new IllegalArgumentException("The new salary cannot be less than the actual salary");
        } else {
            Salary newSalary = new Salary(employee.getSalary().getSalary());
            newSalary.setFechaModificacion(LocalDate.now());
            newSalary = this.iSalaryRepository.saveSalary(newSalary);
            employee.setSalary(newSalary);
            return EmployeeDTO.fromDomain(iEmployeeRepository.updateEmployeeSalary(employee));
        }
    }

    public EmployeeDTO updateEmployeeAndSalary(EmployeeDTO employeeDTO) {
        Employee employeeDb = this.iEmployeeRepository.findEmployeeById(employeeDTO.getId());
        Employee employee = employeeDTO.toDomain(employeeDTO, employeeDTO.getSalary());

        if (Objects.equals(employee.getSalary().getSalary(), employeeDb.getSalary().getSalary())) {
            return EmployeeDTO.fromDomain(iEmployeeRepository.updateEmployee(employee));
        } else if (employee.getSalary().getSalary() == 0) {
            throw new IllegalArgumentException("The new salary cannot be empty");
        } else if (employee.getSalary().getSalary() < employeeDb.getSalary().getSalary()) {
            throw new IllegalArgumentException("The new salary cannot be less than the actual salary");
        } else {
            Salary newSalary = new Salary(employee.getSalary().getSalary());
            newSalary.setFechaModificacion(LocalDate.now());
            newSalary = this.iSalaryRepository.saveSalary(newSalary);
            employee.setSalary(newSalary);
            return EmployeeDTO.fromDomain(iEmployeeRepository.updateEmployee(employee));
        }
    }
}
