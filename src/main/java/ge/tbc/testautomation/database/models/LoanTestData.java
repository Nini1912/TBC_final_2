package ge.tbc.testautomation.database.models;

public class LoanTestData {
    private int id;
    private String description;
    private double loanAmount;
    private int loanTermMonths;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }

    public int getLoanTermMonths() { return loanTermMonths; }
    public void setLoanTermMonths(int loanTermMonths) { this.loanTermMonths = loanTermMonths; }

    @Override
    public String toString() { return description; }
}