package co.com.ias.apiFormatLiquidacionBack.domain.model.employee;

import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class Job {

    private final String value;

    public Job(String value) {
        if (value.length() != 0) {
            if (value.length() > 30) {
                throw new IllegalArgumentException("The job must contain less than 30 digits");
            }
            if (value.length() < 10) {
                throw new IllegalArgumentException("The job must contain more than 10 digits");
            }
            Assert.isTrue(Pattern.matches("^[A-z\\s]+(?<!\\s)$", value), "The job can only contain letters");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}


