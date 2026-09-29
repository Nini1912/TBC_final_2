package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {
    private String iso1;
    private String iso2;
    private double buyRate;
    private double sellRate;
    private int conversionType;
    private int currencyWeight;
    private String updateDate;
}