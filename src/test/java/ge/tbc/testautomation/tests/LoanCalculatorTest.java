package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.database.models.LoanTestData;
import ge.tbc.testautomation.steps.LoanCalculatorSteps;
import ge.tbc.testautomation.steps.LoanSteps;
import ge.tbc.testautomation.steps.NavigationSteps;
import ge.tbc.testautomation.data.LoanDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.LOAN_ERROR_MESSAGE;

public class LoanCalculatorTest extends BaseTest {

    private final LoanTestData testData;

    private NavigationSteps navigationSteps;
    private LoanSteps loanSteps;
    private LoanCalculatorSteps calculatorSteps;

    @Factory(dataProvider = "loanTestData", dataProviderClass = LoanDataProvider.class)
    public LoanCalculatorTest(LoanTestData testData) {
        this.testData = testData;
    }

    @BeforeClass
    public void innerSetUp() {
        navigationSteps = new NavigationSteps(page);
        loanSteps = new LoanSteps(page);
    }

    @Test(priority = 1, description = "სესხების გვერდზე გადასვლა")
    public void navigateToLoansPage() {
        navigationSteps.navigateToForMe()
                .navigateToLoans();
    }

    @Test(priority = 2, dependsOnMethods = "navigateToLoansPage", description = "სესხის კალკულატორის გვერდზე გადასვლა")
    public void navigateToLoanCalculator() {
        loanSteps.clickLoanRequest();
        calculatorSteps = new LoanCalculatorSteps(loanSteps.getLoanCalculatorTab());
    }

    @Test(priority = 3, dependsOnMethods = "navigateToLoanCalculator", description = "დარწმუნება რომ ვიმყოფებით 'სესხის თანხით' ტაბზე")
    public void verifyLoanAmountTabIsActive() {
        calculatorSteps.acceptCookiesIfPresent()
                .verifyLoanAmountTabActive();
    }

    @Test(priority = 4, dependsOnMethods = "verifyLoanAmountTabIsActive", description = "სესხის თანხის შეყვანა")
    public void enterLoanAmount() {
        calculatorSteps.enterLoanAmount(testData.getLoanAmount());
    }

    @Test(priority = 5, dependsOnMethods = "enterLoanAmount", description = "სესხის ვადის არჩევა")
    public void selectLoanTerm() {
        calculatorSteps.selectLoanTerm(testData.getLoanTermMonths());
    }

    @Test(priority = 6, dependsOnMethods = "selectLoanTerm", description = "გამოთვლილი მონაცემების შემოწმება")
    public void  cverifyCalculatedData() {
        double actualAmount = calculatorSteps.getActualLoanAmount();
        int actualTerm = calculatorSteps.getActualLoanTerm();
        double interestRate = calculatorSteps.getInterestRate();

        double expectedMonthlyPayment =
                calculatorSteps.calculateExpectedMonthlyPayment(actualAmount, actualTerm, interestRate);

        calculatorSteps.verifyMonthlyPayment(expectedMonthlyPayment, LOAN_ERROR_MESSAGE);
    }
}