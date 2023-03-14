package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo;

import co.com.ias.apiFormatLiquidacionBack.domain.model.liquidacion.Liquidacion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Liquidacion")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiquidacionDBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
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
    private String auxTransporte;
    private String bono;
    private String totalLiquidacion;
    @ManyToOne
    @JoinColumn(name = "Employee")
    private EmployeeDBO employeeDBO;


    public LiquidacionDBO(String salario, String auxilio, LocalDate fechaInicio, LocalDate fechaFin, String motivo,
                          int diasLaborados, String salarioBase, String primaServicios, String cesantias, String interesesCesantias,
                          String vacaciones, String auxTransporte, String bono, String totalLiquidacion, EmployeeDBO employeeDBO) {
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
        this.employeeDBO = employeeDBO;
    }


    public static Liquidacion toDomain(LiquidacionDBO liquidacionDBO) {
        return new Liquidacion(liquidacionDBO.getSalario(),
                liquidacionDBO.getAuxilio(), liquidacionDBO.getFechaInicio(), liquidacionDBO.getFechaFin(),
                liquidacionDBO.getMotivo(), liquidacionDBO.getDiasLaborados(), liquidacionDBO.getSalarioBase(), liquidacionDBO.getPrimaServicios(),
                liquidacionDBO.getCesantias(), liquidacionDBO.getInteresesCesantias(), liquidacionDBO.getVacaciones(), liquidacionDBO.getAuxTransporte(),
                liquidacionDBO.getBono(), liquidacionDBO.getTotalLiquidacion(), EmployeeDBO.toDomain(liquidacionDBO.getEmployeeDBO()));
    }

    public static LiquidacionDBO fromDomain(Liquidacion liquidacion) {
        return new LiquidacionDBO(liquidacion.getSalario(), liquidacion.getAuxilio(), liquidacion.getFechaInicio(), liquidacion.getFechaFin(),
                liquidacion.getMotivo(), liquidacion.getDiasLaborados(), liquidacion.getSalarioBase(), liquidacion.getPrimaServicios(),
                liquidacion.getCesantias(), liquidacion.getInteresesCesantias(), liquidacion.getVacaciones(), liquidacion.getAuxTransporte(),
                liquidacion.getBono(), liquidacion.getTotalLiquidacion(), EmployeeDBO.fromDomain(liquidacion.getEmployee()));

    }
}
