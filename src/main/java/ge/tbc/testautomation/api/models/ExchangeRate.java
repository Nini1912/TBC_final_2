package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    private String iso1;
    private String iso2;
    private Double buyRate;
    private Double sellRate;
    private Integer conversionType;
    private Integer currencyWeight;
    private String updateDate; // kept as String — the fractional-seconds format is non-standard, safer than fighting a LocalDateTime parser

    public String getIso1() { return iso1; }
    public void setIso1(String iso1) { this.iso1 = iso1; }
    public String getIso2() { return iso2; }
    public void setIso2(String iso2) { this.iso2 = iso2; }
    public Double getBuyRate() { return buyRate; }
    public void setBuyRate(Double buyRate) { this.buyRate = buyRate; }
    public Double getSellRate() { return sellRate; }
    public void setSellRate(Double sellRate) { this.sellRate = sellRate; }
    public Integer getConversionType() { return conversionType; }
    public void setConversionType(Integer conversionType) { this.conversionType = conversionType; }
    public Integer getCurrencyWeight() { return currencyWeight; }
    public void setCurrencyWeight(Integer currencyWeight) { this.currencyWeight = currencyWeight; }
    public String getUpdateDate() { return updateDate; }
    public void setUpdateDate(String updateDate) { this.updateDate = updateDate; }
}