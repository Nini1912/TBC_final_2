package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionInputs {

    private List<FeatureGridItem> featureGridItems;

    public List<FeatureGridItem> getFeatureGridItems() {
        return featureGridItems;
    }
}