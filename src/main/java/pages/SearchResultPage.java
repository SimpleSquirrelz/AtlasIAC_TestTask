package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage{
    private static final String SEARCH_URL = "https://www.booking.com/searchresults.html";

    By searchResultTable = By.id("search_results_table");
    By searchResults = By.xpath("//div[@data-testid='availability-single']");
    By searchPaginationBar = By.xpath("//div[@data-testid='pagination']");

    public SearchResultPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected BasePage openPage() {
        driver.get("SEARCH_URL");
        return this;
    }
}
