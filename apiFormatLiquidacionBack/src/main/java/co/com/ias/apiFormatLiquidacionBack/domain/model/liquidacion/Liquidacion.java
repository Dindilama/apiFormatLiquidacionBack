package co.com.ias.apiFormatLiquidacionBack.domain.model.liquidacion;

import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class Liquidacion {
    private String salario;
    private String auxilio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String motivo;
    private int diasLaborados;
    private String salarioBase;
    private String primaServicios;
    private String cesantias;
    private String interesesCesantias;
    private String vacaciones;
    private Employee employee;

    public String getSalario() {
        return salario;
    }

    public String getAuxilio() {
        return auxilio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public int getDiasLaborados() {
        return diasLaborados;
    }

    public String getSalarioBase() {
        return salarioBase;
    }

    public String getPrimaServicios() {
        return primaServicios;
    }

    public String getCesantias() {
        return cesantias;
    }

    public String getInteresesCesantias() {
        return interesesCesantias;
    }

    public String getVacaciones() {
        return vacaciones;
    }

    public String getAuxTransporte() {
        return auxTransporte;
    }

    public String getBono() {
        return bono;
    }

    public String getTotalLiquidacion() {
        return totalLiquidacion;
    }

    public Employee getEmployee() {
        return employee;
    }

    private String auxTransporte;
    private String bono;
    private String totalLiquidacion;


    public Liquidacion(String salario, String auxilio, LocalDate fechaInicio, LocalDate fechaFin, String motivo,
                       int diasLaborados, String salarioBase, String primaServicios, String cesantias,
                       String interesesCesantias, String vacaciones, String auxTransporte,
                       String bono, String totalLiquidacion, Employee employee) {
        this.salario = salario;
        this.auxilio = auxilio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.motivo = motivo;
        this.diasLaborados = diasLaborados;
        this.salarioBase = salarioBase;
        this.primaServicios = primaServicios;
        this.cesantias = cesantias;
        this.interesesCesantias = interesesCesantias;
        this.vacaciones = vacaciones;
        this.auxTransporte = auxTransporte;
        this.bono = bono;
        this.totalLiquidacion = totalLiquidacion;
        this.employee = employee;
    }


}

