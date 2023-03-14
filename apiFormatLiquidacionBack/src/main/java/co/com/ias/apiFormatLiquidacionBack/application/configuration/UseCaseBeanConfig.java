package co.com.ias.apiFormatLiquidacionBack.application.configuration;

import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IEmployeeRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.IHistorySalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.ILiquidacionRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.ISalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.usecase.EmployeeUseCase;
import co.com.ias.apiFormatLiquidacionBack.domain.usecase.HistorySalaryUseCase;
import co.com.ias.apiFormatLiquidacionBack.domain.usecase.LiquidacionUseCase;
import co.com.ias.apiFormatLiquidacionBack.domain.usecase.SalaryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean
    public EmployeeUseCase employeeUseCase(IEmployeeRepository iEmployeeRepository, ISalaryRepository iSalaryRepository) {
        return new EmployeeUseCase(iEmployeeRepository, iSalaryRepository);
    }

    @Bean
    public SalaryUseCase salaryUseCase(ISalaryRepository iSalaryRepository) {
        return new SalaryUseCase(iSalaryRepository);
    }

    @Bean
    public LiquidacionUseCase liquidacionUseCase(ILiquidacionRepository iLiquidacionRepository, IEmployeeRepository iEmployeeRepository) {
        return new LiquidacionUseCase(iLiquidacionRepository, iEmployeeRepository);
    }

    @Bean
    public HistorySalaryUseCase historySalaryUseCase(IHistorySalaryRepository iHistorySalaryRepository, IEmployeeRepository iEmployeeRepository) {
        return new HistorySalaryUseCase(iHistorySalaryRepository, iEmployeeRepository);
    }

}


