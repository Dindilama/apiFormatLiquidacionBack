package co.com.ias.apiFormatLiquidacionBack.infrastructure.entrypoint;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.HistorySalaryDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.LiquidacionDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.usecase.LiquidacionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/liquidacion")
@CrossOrigin(origins = "http://localhost:4200/")
public class LiquidacionEntryPoint {

    private final LiquidacionUseCase liquidacionUseCase;

    @GetMapping("/getAllLiquidacion")
    public ResponseEntity<?> get() {
        List<LiquidacionDTO> liquidacion = liquidacionUseCase.findAllLiquidaciones();
        return new ResponseEntity(liquidacion, HttpStatus.OK);
    }

    @GetMapping("/getLiquidacionById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity(liquidacionUseCase.findLiquidacionById(id), HttpStatus.OK);
    }

    @PostMapping("/createLiquidacion")
    public ResponseEntity<?> create(@RequestBody LiquidacionDTO liquidacionDTO) {
        return new ResponseEntity(liquidacionUseCase.saveLiquidacion(liquidacionDTO), HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteLiquidacion/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity(liquidacionUseCase.deleteLiquidacion(id), HttpStatus.OK);
    }

    @GetMapping("/getLiquidacionByEmployee/{id}")
    public ResponseEntity<?> getLiquidacionByEmployee(@PathVariable(name = "id") Long id) {
        List<LiquidacionDTO> historyLiquidacion = liquidacionUseCase.findLiquidacionByEmployeeId(id);
        return new ResponseEntity<>(historyLiquidacion, HttpStatus.OK);
    }
}
