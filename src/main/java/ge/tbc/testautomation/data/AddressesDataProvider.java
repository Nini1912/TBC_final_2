package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

import java.util.List;

import static ge.tbc.testautomation.data.Constants.FILTER_247;
import static ge.tbc.testautomation.data.Constants.FILTER_OPEN;

public class AddressesDataProvider {

    @DataProvider(name = "filterCombinations")
    public static Object[][] filterCombinations() {
        return new Object[][]{
                {List.of(FILTER_247)},
                {List.of(FILTER_OPEN)},
                {List.of(FILTER_247, FILTER_OPEN)}
        };
    }
}