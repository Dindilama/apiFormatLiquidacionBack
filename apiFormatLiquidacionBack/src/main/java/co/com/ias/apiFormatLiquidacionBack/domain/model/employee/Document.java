package co.com.ias.apiFormatLiquidacionBack.domain.model.employee;

import static org.springframework.util.Assert.notNull;

public class Document {

    private final String value;

    public Document(String value) {
        notNull(value, "The document cannot be empty");
        String val = value.toString();
        if (val.length() < 7) {
            throw new IllegalArgumentException("The document must contain 7 digits");
        }
        if(val.length() > 15){
            throw new IllegalArgumentException("The document must contain 15 digits");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}