package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AttractionsSearchResultCard {

    private WebElement card;
    private final By cardLocation = By.xpath(".//h4//preceding-sibling::div");
    private final By cardTitle = By.xpath(".//h4");

    public AttractionsSearchResultCard(WebElement card) {
        this.card = card;
    }

    public String getLocationText() {
        return card.findElement(cardLocation).getText();
    }

    public String getTitleText() {
        return card.findElement(cardTitle).getText();
    }
}
