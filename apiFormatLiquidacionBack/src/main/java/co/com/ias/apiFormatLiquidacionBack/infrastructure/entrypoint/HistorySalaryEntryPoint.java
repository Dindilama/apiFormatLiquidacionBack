package co.com.ias.apiFormatLiquidacionBack.infrastructure.entrypoint;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.HistorySalaryDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.usecase.HistorySalaryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historysalary")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class HistorySalaryEntryPoint {
    private final HistorySalaryUseCase historySalaryUseCase;

    @GetMapping("/getAllSalariesById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        List<HistorySalaryDTO> historySalaryDTOS = historySalaryUseCase.findSalaryById(id);
        return new ResponseEntity<>(historySalaryDTOS, HttpStatus.OK);
    }
}


