package co.com.ias.apiFormatLiquidacionBack.domain.model.dto;


import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.liquidacion.Liquidacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiquidacionDTO {

    private Long idEmployee;
    private LocalDate fechaFin;
    private String motivo;
    private Integer id;
    private String aux_transporte;
    private String auxilio;
    private String bono;
    private String cesantias;
    private Integer dias_laborados;
    private LocalDate fecha_fin;
    private LocalDate fecha_inicio;
    private String intereses_cesantias;
    private String prima_servicios;
    private String salario;
    private String salario_base;
    private String total_liquidacion;
    private String vacaciones;
    private Integer employee;


    public String getMotivo() {
        return motivo;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public LiquidacionDTO(Long idEmployee, LocalDate fechaFin, String motivo, Integer id, String aux_transporte,
                          String auxilio, String bono, String cesantias, Integer dias_laborados,
                          LocalDate fecha_fin, LocalDate fecha_inicio, String intereses_cesantias,
                          String prima_servicios, String salario, String salario_base, String total_liquidacion,
                          String vacaciones, Integer employee) {
        this.idEmployee = idEmployee;
        this.fechaFin = fechaFin;
        this.motivo = motivo;
        this.id = id;
        this.aux_transporte = aux_transporte;
        this.auxilio = auxilio;
        this.bono = bono;
        this.cesantias = cesantias;
        this.dias_laborados = dias_laborados;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.intereses_cesantias = intereses_cesantias;
        this.prima_servicios = prima_servicios;
        this.salario = salario;
        this.salario_base = salario_base;
        this.total_liquidacion = total_liquidacion;
        this.vacaciones = vacaciones;
        this.employee = employee;
    }

    public static LiquidacionDTO fromDomain(Liquidacion liquidacion) {
        return new LiquidacionDTO(
                liquidacion.getEmployee().getIdEmployee(),
                liquidacion.getFechaFin(),
                liquidacion.getMotivo(),
                liquidacion.getDiasLaborados(),
                liquidacion.getAuxTransporte(),
                liquidacion.getAuxilio(),
                liquidacion.getBono(),
                liquidacion.getCesantias(),
                liquidacion.getDiasLaborados(),
                liquidacion.getFechaFin(),
                liquidacion.getFechaInicio(),
                liquidacion.getInteresesCesantias(),
                liquidacion.getPrimaServicios(),
                liquidacion.getSalario(),
                liquidacion.getSalarioBase(),
                liquidacion.getTotalLiquidacion(),
                liquidacion.getVacaciones(),
                liquidacion.getDiasLaborados());
    }

    public static Liquidacion toDomain(String salario, String auxilio, LocalDate fechaInicio, LocalDate fechaFin, String motivo,
                                       int diasLaborados, String salarioBase, String primaServicios, String cesantias, String interesesCesantias,
                                       String vacaciones, String auxTransporte, String bono, String totalLiquidacion, Employee employee) {
        return new Liquidacion(salario, auxilio, fechaInicio, fechaFin, motivo, diasLaborados, salarioBase, primaServicios,
                cesantias, interesesCesantias, vacaciones, auxTransporte, bono, totalLiquidacion, employee);
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getAux_transporte() {
        return aux_transporte;
    }

    public void setAux_transporte(String aux_transporte) {
        this.aux_transporte = aux_transporte;
    }

    public String getAuxilio() {
        return auxilio;
    }

    public void setAuxilio(String auxilio) {
        this.auxilio = auxilio;
    }

    public String getBono() {
        return bono;
    }

    public void setBono(String bono) {
        this.bono = bono;
    }

    public String getCesantias() {
        return cesantias;
    }

    public void setCesantias(String cesantias) {
        this.cesantias = cesantias;
    }

    public Integer getDias_laborados() {
        return dias_laborados;
    }

    public void setDias_laborados(Integer dias_laborados) {
        this.dias_laborados = dias_laborados;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getIntereses_cesantias() {
        return intereses_cesantias;
    }

    public void setIntereses_cesantias(String intereses_cesantias) {
        this.intereses_cesantias = intereses_cesantias;
    }

    public String getPrima_servicios() {
        return prima_servicios;
    }

    public void setPrima_servicios(String prima_servicios) {
        this.prima_servicios = prima_servicios;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(String salario_base) {
        this.salario_base = salario_base;
    }

    public String getTotal_liquidacion() {
        return total_liquidacion;
    }

    public void setTotal_liquidacion(String total_liquidacion) {
        this.total_liquidacion = total_liquidacion;
    }

    public String getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(String vacaciones) {
        this.vacaciones = vacaciones;
    }
}
