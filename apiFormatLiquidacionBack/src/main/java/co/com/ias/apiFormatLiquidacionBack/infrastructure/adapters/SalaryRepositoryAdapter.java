package co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters;

import co.com.ias.apiFormatLiquidacionBack.domain.model.gateway.ISalaryRepository;
import co.com.ias.apiFormatLiquidacionBack.domain.model.salary.Salary;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.ISalaryRepositoryAdapter;
import co.com.ias.apiFormatLiquidacionBack.infrastructure.adapters.jpa.entity.dbo.SalaryDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class SalaryRepositoryAdapter implements ISalaryRepository {

    private final ISalaryRepositoryAdapter iSalaryRepositoryAdapter;

    @Override
    public Salary findSalaryById(Long id) {
        Optional<SalaryDBO> dbo = iSalaryRepositoryAdapter.findById(id);
        if (dbo.isEmpty()) {
            throw new NullPointerException("Not exist salary with id: " + id);
        } else {
            return SalaryDBO.toDomain(dbo.get());
        }
    }

    @Override
    public Salary saveSalary(Salary salary) {
        SalaryDBO salaryDBO = SalaryDBO.fromDomain(salary);
        SalaryDBO salarySaved = iSalaryRepositoryAdapter.save(salaryDBO);
        return SalaryDBO.toDomain(salarySaved);
    }

    @Override
    public Salary updateSalary(Salary salary) {
        SalaryDBO dbo = SalaryDBO.fromDomain(salary);
        Optional<SalaryDBO> elementFound = iSalaryRepositoryAdapter.findById(dbo.getIdSalary());
        if (elementFound.isEmpty()) {
            throw new NullPointerException("This salary not exist with this id: " + salary.getId().getClass());

        } else {
            SalaryDBO salarySaved = iSalaryRepositoryAdapter.save(dbo);
            return SalaryDBO.toDomain(salarySaved);
        }
    }

    @Override
    public List<Salary> findAllSalary() {
        return iSalaryRepositoryAdapter.findAll().stream().map(SalaryDBO::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteSalary(Long salary) {
        AtomicReference<Boolean> bool = new AtomicReference<>(false);
        Optional<SalaryDBO> dbo = iSalaryRepositoryAdapter.findById(salary);
        dbo.ifPresent(value -> {
            iSalaryRepositoryAdapter.deleteById(salary);
            bool.set(true);
        });
        return bool.get();
    }
}

