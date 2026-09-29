package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.components.NavigationComponent;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.LoanDataProvider;
import ge.tbc.testautomation.database.models.LoanTestData;
import ge.tbc.testautomation.steps.LoanCalculatorSteps;
import ge.tbc.testautomation.steps.LoanSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class LoanCalculatorTest extends BaseTest {

    private final LoanTestData testData;

    NavigationComponent navigationComponent;
    LoanSteps loanSteps;
    LoanCalculatorSteps calculatorSteps;

    @Factory(dataProvider = "loanTestData", dataProviderClass = LoanDataProvider.class)
    public LoanCalculatorTest(LoanTestData testData) {
        this.testData = testData;
    }

    @BeforeClass
    public void innerSetUp() {
        navigationComponent = new NavigationComponent(page);
        loanSteps = new LoanSteps(page);
    }

    @Test(priority = 1, description = "KAN-T37/KAN-T38 სესხების გვერდზე გადასვლა")
    public void navigateToLoansPage() {
        navigationComponent.navigateToForMe()
                .navigateToLoans();
    }

    @Test(priority = 2, description = "KAN-T37/KAN-T38 სესხის კალკულატორის გვერდზე გადასვლა")
    public void navigateToLoanCalculator() {
        loanSteps.clickLoanRequest();
        calculatorSteps = new LoanCalculatorSteps(loanSteps.getLoanCalculatorTab());
    }

    @Test(priority = 3, description = "KAN-T37/KAN-T38 დარწმუნება რომ ვიმყოფებით 'სესხის თანხით' ტაბზე")
    public void verifyLoanAmountTabIsActive() {
        calculatorSteps.acceptCookiesIfPresent()
                .verifyLoanAmountTabActive();
    }

    @Test(priority = 4, description = "KAN-T37/KAN-T38 სესხის თანხის შეყვანა")
    public void enterLoanAmount() {
        calculatorSteps.enterLoanAmount(testData.getLoanAmount());
    }

    @Test(priority = 5, description = "KAN-T37/KAN-T38 სესხის ვადის არჩევა")
    public void selectLoanTerm() {
        calculatorSteps.selectLoanTerm(testData.getLoanTermMonths());
    }

    @Test(priority = 6, description = "KAN-T37/KAN-T38 გამოთვლილი მონაცემების შემოწმება")
    public void verifyCalculatedData() {
        double actualAmount = calculatorSteps.getActualLoanAmount();
        int actualTerm = calculatorSteps.getActualLoanTerm();
        double interestRate = calculatorSteps.getInterestRate();

        double expectedMonthlyPayment =
                calculatorSteps.calculateExpectedMonthlyPayment(
                        actualAmount,
                        actualTerm,
                        interestRate
                );

        calculatorSteps.verifyMonthlyPayment(
                expectedMonthlyPayment,
                Constants.LOAN_ERROR_MESSAGE
        );
    }
}