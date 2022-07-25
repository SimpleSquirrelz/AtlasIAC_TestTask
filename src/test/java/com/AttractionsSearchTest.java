package com;

import components.AttractionsSearchResultCard;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AttractionsPage;
import pages.AttractionsSearchResultPage;

import java.util.List;


public class AttractionsSearchTest extends BaseTest {

    @Test
    public void verifyAttractionsSearch() {
        String destination = "New York";

        AttractionsPage attractionsPage = (AttractionsPage) homePage.openPage()
                .clickNavigationButton("Attractions");
        AttractionsSearchResultPage searchResultPage = attractionsPage.enterDestination(destination)
                .clickSearchButton();
        int amountOfPages = (int) Math.ceil((double) searchResultPage.getResultsAmount() / searchResultPage.getPaginationValue());

        SoftAssert softAss = new SoftAssert();
        for (int i = 1; i < amountOfPages; i++) {
            List<AttractionsSearchResultCard> cards = searchResultPage.getSearchResults();
            for (AttractionsSearchResultCard card : cards) {
                String assertionErrorText = String.format("'%s' card at page %s location is invalid", card.getTitleText(), i);
                softAss.assertEquals(card.getLocationText(), destination, assertionErrorText);
            }
            if (i != amountOfPages - 1)
                searchResultPage.clickNextPageButton();
        }
        softAss.assertAll();
    }
}
