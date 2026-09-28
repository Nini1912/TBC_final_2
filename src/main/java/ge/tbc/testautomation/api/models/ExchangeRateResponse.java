package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {

    private String iso1;
    private String iso2;
    private double buyRate;
    private double sellRate;
    private int conversionType;
    private int currencyWeight;
    private String updateDate;

    public String getIso1() {
        return iso1;
    }

    public String getIso2() {
        return iso2;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public int getConversionType() {
        return conversionType;
    }

    public int getCurrencyWeight() {
        return currencyWeight;
    }

    public String getUpdateDate() {
        return updateDate;
    }
}