package co.com.ias.apiFormatLiquidacionBack.domain.model.dto;

import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class SalaryDTO {
    private Long id;

    private Double value;

    private LocalDate fechaModificacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public Double getSalary() {
        return value;
    }

    public void setSalary(Double value) {
        this.value = value;
    }

    public SalaryDTO(Long id, Double value, LocalDate fechaModificacion) {
        this.id = id;
        this.value = value;
        this.fechaModificacion = fechaModificacion;
    }

    public static SalaryDTO fromDomain(Salary salary) {
        return new SalaryDTO(salary.getId(),
                salary.getSalary(), salary.getFechaModificacion());
    }

    public Salary toDomain(SalaryDTO salaryDTO) {
        return new Salary(
                salaryDTO.getId(),
                salaryDTO.getSalary(), salaryDTO.getFechaModificacion());
    }
}
