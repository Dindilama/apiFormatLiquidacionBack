package co.com.ias.apiFormatLiquidacionBack.domain.usecase;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.SalaryDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.ISalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryUseCase {

    private final ISalaryRepository iSalaryRepository;


    public SalaryUseCase(ISalaryRepository iSalaryRepository) {
        this.iSalaryRepository = iSalaryRepository;
    }

    public SalaryDTO saveSalary(SalaryDTO salaryDTO) {
        Salary salary = salaryDTO.toDomain(salaryDTO);
        return SalaryDTO.fromDomain(this.iSalaryRepository.saveSalary(salary));
    }

    public SalaryDTO findSalaryById(Long id) {
        Salary salary = this.iSalaryRepository.findSalaryById(id);
        if (salary != null) {
            return SalaryDTO.fromDomain(salary);
        }
        return null;
    }


    public ArrayList<SalaryDTO> findAllSalaries() {
        List<Salary> salary = this.iSalaryRepository.findAllSalary();
        return (ArrayList<SalaryDTO>) salary.stream().map(SalaryDTO::fromDomain).collect(Collectors.toList());
    }


    public Boolean deleteSalary(Long id) {
        return iSalaryRepository.deleteSalary(id);
    }

    public SalaryDTO updateSalary(SalaryDTO salaryDTO) {
        Salary salary = salaryDTO.toDomain(salaryDTO);
        return SalaryDTO.fromDomain(iSalaryRepository.updateSalary(salary));
    }
}
