package co.com.ias.apiFormatLiquidacionBack.domain.model.employee;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class Name {
    private final String value;

    public Name(String value) {
        Assert.isTrue(StringUtils.isNotBlank(value), "The name cannot be empty");
        if (value.length() > 50) {
            throw new IllegalArgumentException("The name must contain less than 50 digits");
        }
        Assert.isTrue(Pattern.matches("^[A-z\\s]+(?<!\\s)$", value), "The name can only contain letters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
