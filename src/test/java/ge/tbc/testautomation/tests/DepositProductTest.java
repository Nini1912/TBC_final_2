package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.components.NavigationComponent;
import ge.tbc.testautomation.steps.DepositSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DepositProductTest extends BaseTest {

    private NavigationComponent navigationComponent;
    private DepositSteps depositSteps;

    @BeforeClass
    public void innerSetUp() {
        navigationComponent = new NavigationComponent(page);
        depositSteps = new DepositSteps(page);
    }

    @Test(priority = 1, description = "ანაბრების გვერდზე გადასვლა")
    public void navigateToDepositsPage() {
        navigationComponent
                .navigateToForMe()
                .navigateToDeposits();

        depositSteps.verifyDepositsPageOpened();
    }

    @Test(priority = 2, description = "ვადიანი ანაბრის არჩევა")
    public void selectTermDeposit() {
        depositSteps
                .selectTermDeposit()
                .verifyTermDepositPageOpened();
    }

    @Test(priority = 3, description = "ვადიანი ანაბრის ძირითადი ინფორმაციის შემოწმება")
    public void verifyTermDepositInformation() {
        depositSteps.verifyDepositConditionsDisplayed();
    }
}