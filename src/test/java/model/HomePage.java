package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {
    public static final String SEARCH_TEXT = "Samsung S25 Ultra";
    public static final String PHONE_BRAND_TEXT = "Samsung";
    public static final String PHONE_MODEL_TEXT = "S25 Ultra";

    @FindBy(id = "gh-search-input")
    private WebElement searchInput;

    @FindBy(css = "button.header-search-button")
    private WebElement searchButton;

    @FindBy(css = "h4.sku-title a")
    private List<WebElement> searchResults;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchForItem() {
        getWait10().until(ExpectedConditions.visibilityOf(searchInput)).sendKeys(SEARCH_TEXT);
        searchButton.click();
    }

    public boolean areResultsValid() {
        getWait10().until(ExpectedConditions.visibilityOfAllElements(searchResults));

        for (WebElement result : searchResults) {
            String resultText = result.getText();
            if (!(resultText.contains(PHONE_BRAND_TEXT) && resultText.contains(PHONE_MODEL_TEXT))) {
                return false;
            }
        }
        return true;
    }

    public boolean isSearchFieldCorrect() {
        getWait10().until(ExpectedConditions.attributeToBe(searchInput, "value", SEARCH_TEXT.toLowerCase()));
        return searchInput.getAttribute("value").equalsIgnoreCase(SEARCH_TEXT);
    }
}
