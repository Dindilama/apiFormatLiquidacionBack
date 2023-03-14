package co.com.ias.apiFormatLiquidacionBack.infrastructure.entrypoint;

import co.com.ias.apiFormatLiquidacionBack.domain.model.dto.EmployeeDTO;
import co.com.ias.apiFormatLiquidacionBack.domain.usecase.EmployeeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeEntryPoint {

    private final EmployeeUseCase employeeUseCase;

    @GetMapping("/getAllEmployees")
    public ResponseEntity<?> get() {
        List<EmployeeDTO> employee = employeeUseCase.findAllEmployees();
        return new ResponseEntity(employee, HttpStatus.OK);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity(employeeUseCase.findEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity(employeeUseCase.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity(employeeUseCase.updateEmployee(employeeDTO), HttpStatus.OK);
    }

    @PutMapping("/updateEmployeeAndSalary")
    public ResponseEntity<?> updateEmployeeAndSalary(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity(employeeUseCase.updateEmployeeAndSalary(employeeDTO), HttpStatus.OK);
    }


    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity(employeeUseCase.deleteEmployee(id), HttpStatus.OK);
    }
}
