package co.com.ias.apiFormatLiquidacionBack.domain.model.salary;

import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.springframework.util.Assert.notNull;

@NoArgsConstructor
public class Salary {

    private Long id;
    private Double salary;

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @DateTimeFormat()
    private LocalDate fechaModificacion;

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public Salary(Double salary) {
        notNull(salary, "The salary cannot be empty");
        if (salary <= 0) {
            throw new IllegalArgumentException("The salary cannot be empty");
        }
        if (salary < 100000) {
            throw new IllegalArgumentException("The salary cannot be less than current SVML");
        }
        if (salary > 7000000) {
            throw new IllegalArgumentException("The salary cannot be more than 7.000.000 COP");
        }
        this.salary = salary;
    }

    public Salary(Long id, double salary, LocalDate fechaModificacion) {
        this.id = id;
        this.salary = salary;
        this.fechaModificacion = fechaModificacion;
    }
}
