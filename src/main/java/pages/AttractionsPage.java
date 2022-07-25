package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AttractionsPage extends BasePage {

    private final String ATTRACTIONS_URL = "https://www.booking.com/attractions";
    private final By searchField = By.xpath("//input[@data-testid='search-input-field']");
    private final By searchBarResults = By.xpath("//a[@data-testid='search-bar-result']");
    private final By searchButton = By.xpath("//button[@type='submit']");

    public AttractionsPage enterDestination(String destination) {
        driver.findElement(searchField).sendKeys(destination);
        getSearchBarResults().get(0).click();
        return this;
    }

    public AttractionsSearchResultPage clickSearchButton() {
        driver.findElement(searchButton).click();
        return new AttractionsSearchResultPage(driver);
    }

    private List<WebElement> getSearchBarResults() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchBarResults));
    }

    public AttractionsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected BasePage openPage() {
        driver.get(ATTRACTIONS_URL);
        return this;
    }
}
