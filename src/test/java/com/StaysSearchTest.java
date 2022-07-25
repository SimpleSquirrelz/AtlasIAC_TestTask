package com;

import org.testng.annotations.Test;
import components.StaysSearchResultCard;
import org.testng.asserts.SoftAssert;
import pages.StaysSearchResultPage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class StaysSearchTest extends BaseTest {

    @Test
    public void verifyStaysSearch() {
        String destination = "New York";
        LocalDate dateFrom = LocalDate.of(2022, 12, 1);
        LocalDate dateTo = LocalDate.of(2022, 12, 30);

        int daysDifference = (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
        String expectedNights = daysDifference + " night";

        StaysSearchResultPage searchResultPage = homePage.openPage()
                .enterDestination(destination)
                .selectDateRange(dateFrom, dateTo)
                .clickSearchButton();

        int amountOfPages = searchResultPage.getAmountOfPages();

        SoftAssert softAss = new SoftAssert();

        for (int i = 1; i < amountOfPages; i++) {
            List<StaysSearchResultCard> cards = searchResultPage.getSearchResults();
            for (StaysSearchResultCard card : cards) {
                softAss.assertTrue(card.getLocationText().contains(destination), String.format("Card at %s page location is invalid", i));
                softAss.assertTrue(card.getNightsText().contains(expectedNights), String.format("Card at %s page nights is invalid", i));
            }
            if (i != amountOfPages - 1)
                searchResultPage.clickNextPageButton();
        }

        softAss.assertAll();
    }
}
