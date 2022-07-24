package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPage extends BasePage {

    private final String SEARCH_URL = "https://www.booking.com/searchresults.html";

    private final By searchResultCard = By.xpath("//div[@data-testid='property-card']");
    private final By nextPageButton = By.xpath("//button[@aria-label='Next page']");
    private final By overlayCard = By.xpath("//*[@data-testid='overlay-card']");
    private final By lastPageButton = By.xpath("(//div[@data-testid='pagination']//ol//li//button)[last()]");

    public SearchResultPage(WebDriver driver){
        super(driver);
    }

    public SearchResultPage clickNextPageButton(){
        driver.findElement(nextPageButton).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlayCard));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayCard));
        return this;
    }

    public List<SearchResultCard> getSearchResults() {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultCard))
                .stream().map(SearchResultCard::new)
                .collect(Collectors.toList());
    }

    public int getAmountOfPages(){
        return Integer.parseInt(driver.findElement(lastPageButton).getText());
    }

    @Override
    protected BasePage openPage() {
        driver.get(SEARCH_URL);
        return this;
    }
}
