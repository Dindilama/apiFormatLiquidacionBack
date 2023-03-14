package co.com.ias.apiFormatLiquidacionBack.domain.model.employee;

import java.time.LocalDate;

import static org.springframework.util.Assert.notNull;

public class StartDate {
    private final LocalDate value;

    public StartDate(LocalDate value) {
        LocalDate dateMax = LocalDate.of(2015, 6, 6); //
        LocalDate dateMin = LocalDate.of(2015, 1, 1); //

        notNull(value, "The start date cannot be empty");

        if (value.isAfter(dateMax)) {
            throw new IllegalArgumentException("The start date cannot be after 06/06/2015");
        }
        if (value.isBefore(dateMin)) {
            throw new IllegalArgumentException("The start date cannot be before 01/01/2015");
        }

        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

}
