package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class HomePage extends BasePage{
    private static final String HOMEPAGE_URL = "https://www.booking.com";
    By destinationField = By.id("ss");
    By destinationSelect = By.cssSelector("ul[role='listbox']>li");
    By searchButton = By.cssSelector(".sb-searchbox__button");
    By selectDate = By.cssSelector(".xp__dates");
    By calendar = By.cssSelector(".bui-calendar__main");
    By calendarNextButton = By.xpath("//div[@data-bui-ref='calendar-next']");
    By calendarTable = By.cssSelector(".bui-calendar__dates");


    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage(){
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public HomePage enterDestination(String destination){
        driver.findElement(destinationField).sendKeys(destination);

        WebElement destinationFieldSelect = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(destinationSelect));
        destinationFieldSelect.click();
        return this;
    }

    public HomePage selectDate(LocalDate from, LocalDate to) throws InterruptedException {
        driver.findElement(selectDate).click();
        driver.findElement(selectDate).click();

        nextMonth(calculateMonthDifference(LocalDate.now(), from));
        List<WebElement> monthValues = driver.findElements(calendarTable).get(0).findElements(By.tagName("td"));
        clickGivenDay(monthValues, String.valueOf(from.getDayOfMonth()));
        nextMonth(calculateMonthDifference(from, to));
        clickGivenDay(monthValues, String.valueOf(to.getDayOfMonth()));

        return this;
    }

    public SearchResultPage clickSearchButton(){
        driver.findElement(searchButton).click();
        return new SearchResultPage(this.driver);
    }

    private int calculateMonthDifference(LocalDate date1, LocalDate date2){
        return Math.max(date1.getMonthValue(), date2.getMonthValue()) - Math.min(date1.getMonthValue(), date2.getMonthValue());
    }

    private void nextMonth(int value){
        for(int i = 0; i < value;i++){
            driver.findElement(calendarNextButton).click();
            new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendar));
        }
    }

    private void clickGivenDay(List<WebElement> elementList, String dayOfMonth){
        elementList.stream()
                .filter(el -> el.getText().contains(dayOfMonth))
                .findFirst()
                .ifPresent(WebElement::click);
    }
}
