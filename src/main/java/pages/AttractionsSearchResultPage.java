package pages;

import components.AttractionsSearchResultCard;
import components.StaysSearchResultCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AttractionsSearchResultPage extends BasePage {

    private final String ATTRACTIONS_SEARCH_RESULT_URL = "https://www.booking.com/attractions/searchresults";

    private final By searchResultCard = By.xpath("//*[@data-testid='card']");
    private final By nextPageButton = By.xpath("(//div[@role='group']//button[@type='button'])[2]");
    private final By resultCounterLabel = By.xpath("//span[contains(text(), 'Showing')]");
    private final By paginationSelect = By.id("__bui-7");


    protected AttractionsSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<AttractionsSearchResultCard> getSearchResults() {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultCard))
                .stream().map(AttractionsSearchResultCard::new)
                .collect(Collectors.toList());
    }

    public AttractionsSearchResultPage clickNextPageButton() {
        driver.findElement(nextPageButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultCard));
        return this;
    }

    public int getResultsAmount() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultCard));
        String resultsAmountText = driver.findElement(resultCounterLabel).getText();
        return Integer.parseInt(resultsAmountText.split(" of ")[1]);
    }

    public int getPaginationValue() {
        return Integer.parseInt(new Select(driver.findElement(paginationSelect)).getFirstSelectedOption().getAttribute("value"));
    }

    @Override
    protected BasePage openPage() {
        driver.get(ATTRACTIONS_SEARCH_RESULT_URL);
        return this;
    }
}
