package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionComponent {

    private String type;
    private SectionInputs inputs;

    public String getType() {
        return type;
    }

    public SectionInputs getInputs() {
        return inputs;
    }
}