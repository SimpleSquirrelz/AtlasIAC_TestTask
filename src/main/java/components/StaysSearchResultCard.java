package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StaysSearchResultCard {

    private WebElement card;
    private final By cardAddress = By.xpath(".//span[@data-testid='address']");
    private final By cardNights = By.xpath(".//div[@data-testid='price-for-x-nights']");

    public StaysSearchResultCard(WebElement card) {
        this.card = card;
    }

    public String getLocationText() {
        return card.findElement(cardAddress).getText();
    }

    public String getNightsText() {
        return card.findElement(cardNights).getText();
    }

}
