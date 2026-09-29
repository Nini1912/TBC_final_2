package ge.tbc.testautomation.database.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTestData {

    private int id;
    private String description;
    private double loanAmount;
    private int loanTermMonths;

    @Override
    public String toString() {
        return description;
    }
}