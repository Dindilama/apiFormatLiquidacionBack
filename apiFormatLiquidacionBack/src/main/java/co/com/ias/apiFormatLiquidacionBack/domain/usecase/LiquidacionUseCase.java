package co.com.ias.apiFormatLiquidacionBack.domain.usecase;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.LiquidacionDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.model.employee.Employee;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IEmployeeRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.ILiquidacionRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.liquidacion.Liquidacion;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.EmployeeDBO;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LiquidacionUseCase {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private final ILiquidacionRepository iLiquidacionRepository;
    private final IEmployeeRepository iEmployeeRepository;

    public LiquidacionUseCase(ILiquidacionRepository iLiquidacionRepository, IEmployeeRepository iEmployeeRepository) {
        this.iLiquidacionRepository = iLiquidacionRepository;
        this.iEmployeeRepository = iEmployeeRepository;
    }

    public LiquidacionDTO saveLiquidacion(LiquidacionDTO liquidacionDTO) {
        Employee employeeById = this.iEmployeeRepository.findEmployeeById(liquidacionDTO.getIdEmployee());
        Employee employee = this.iEmployeeRepository.findEmployeeById(liquidacionDTO.getIdEmployee());

        //variables
        double primaServicio;
        double cesantias;
        double interesesCesantias;
        double vacaciones;
        double auxTransporte;
        double bono = 0d;
        double totalLiquidacion;

        Double salaryBase = employeeById.getSalary().getSalary();
        LocalDate startDate = employeeById.getStartDate().getValue();
        Period period = Period.between(startDate, liquidacionDTO.getFechaFin());
        int diasLaborados = period.getDays();
        int primerAño = diasLaborados % 360;

        if ("VOLUNTARIO".equals(liquidacionDTO.getMotivo()) || "JUSTIFICADO".equals(liquidacionDTO.getMotivo())) {
            primaServicio = (salaryBase * diasLaborados) / 360;
            cesantias = (salaryBase * diasLaborados) / 360;
            interesesCesantias = (cesantias * diasLaborados * 0.12) / 360;
            vacaciones = (salaryBase * diasLaborados) / 720;
            auxTransporte = 102854;
            totalLiquidacion = primaServicio + cesantias + interesesCesantias + vacaciones + auxTransporte;
        } else {
            primaServicio = (salaryBase * diasLaborados) / 360;
            cesantias = (salaryBase * diasLaborados) / 360;
            interesesCesantias = (cesantias * diasLaborados * 0.12) / 360;
            vacaciones = (salaryBase * diasLaborados) / 720;
            auxTransporte = 102854;
            if (diasLaborados > 360) {
                bono = salaryBase + ((salaryBase / 30) * 20) * primerAño;
            }
            totalLiquidacion = primaServicio + cesantias + interesesCesantias + vacaciones + auxTransporte + bono;
        }


        Liquidacion liquidacion = LiquidacionDTO.toDomain(df.format(salaryBase), df.format(auxTransporte), startDate, liquidacionDTO.getFechaFin(),
                liquidacionDTO.getMotivo(), diasLaborados, df.format(salaryBase), df.format(primaServicio), df.format(cesantias), df.format(interesesCesantias),
                df.format(vacaciones), df.format(auxTransporte), df.format(bono), df.format(totalLiquidacion), employee);
        return LiquidacionDTO.fromDomain(this.iLiquidacionRepository.saveLiquidacion(liquidacion, employee));
    }

    public LiquidacionDTO findLiquidacionById(Long id) {
        Liquidacion liquidacion = this.iLiquidacionRepository.findLiquidacionById(id);
        if (liquidacion != null) {
            return LiquidacionDTO.fromDomain(liquidacion);
        }
        return null;
    }


    public ArrayList<LiquidacionDTO> findAllLiquidaciones() {
        List<Liquidacion> liquidaciones = this.iLiquidacionRepository.findAllLiquidacion();
        return (ArrayList<LiquidacionDTO>) liquidaciones.stream().map(LiquidacionDTO::fromDomain).collect(Collectors.toList());
    }


    public Boolean deleteLiquidacion(Long id) {
        return iLiquidacionRepository.deleteLiquidacion(id);
    }


    public List<LiquidacionDTO> findLiquidacionByEmployeeId(Long id) {
        Employee employeeById = this.iEmployeeRepository.findEmployeeById(id);
        EmployeeDBO employeeDBO = EmployeeDBO.fromDomain(employeeById);
        List<Liquidacion> historyByEmployee = this.iLiquidacionRepository.findHistoryByEmployee(employeeDBO);
        return historyByEmployee.stream().map(LiquidacionDTO::fromDomain).collect(Collectors.toList());
    }
}
