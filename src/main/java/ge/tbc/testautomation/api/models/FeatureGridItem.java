package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureGridItem {

    private String subTitle;

    public String getSubTitle() {
        return subTitle;
    }
}