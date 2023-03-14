package co.com.ias.apiFormatLiquidacionBack.infrastructure.entrypoint;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.SalaryDTO;

import co.com.ias.apiFormatLiquidacionBack.domain.usecase.SalaryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class SalaryEntryPoint {
    private final SalaryUseCase salaryUseCase;

    @GetMapping("/getAllSalaries")
    public ResponseEntity<?> get() {
        List<SalaryDTO> salary = salaryUseCase.findAllSalaries();
        return new ResponseEntity(salary, HttpStatus.OK);
    }

    @GetMapping("/getAllSalaries/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity(salaryUseCase.findSalaryById(id), HttpStatus.OK);
    }

    @PostMapping("/createSalary")
    public ResponseEntity<?> create(@RequestBody SalaryDTO salaryDTO) {
        return new ResponseEntity(salaryUseCase.saveSalary(salaryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/updateSalary")
    public ResponseEntity<?> update(@RequestBody SalaryDTO salaryDTO) {
        return new ResponseEntity(salaryUseCase.updateSalary(salaryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteSalary/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity(salaryUseCase.deleteSalary(id), HttpStatus.OK);
    }
}
