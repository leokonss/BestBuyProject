package tests;

import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    @Test
    public void testSearchResults() {
        HomePage homePage = new HomePage(getDriver());
        homePage.searchForItem();

        boolean resultsValid = homePage.areResultsValid();
        boolean searchFieldCorrect = homePage.isSearchFieldCorrect();

        Assert.assertTrue(searchFieldCorrect, String.format("Search field does not contain expected text: %s.",
                HomePage.SEARCH_TEXT));
        Assert.assertTrue(resultsValid, String.format("One or more search results do not match expected keywords: %s.",
                HomePage.SEARCH_TEXT));
    }
}
