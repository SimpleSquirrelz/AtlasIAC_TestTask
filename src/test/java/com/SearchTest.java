package com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultCard;
import pages.SearchResultPage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SearchTest {

    private HomePage homePage;
    private WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("lang=en");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test()
    public void verifySearchFilters() throws InterruptedException {
        String destination = "New York";
        LocalDate dateFrom = LocalDate.of(2022, 12, 1);
        LocalDate dateTo = LocalDate.of(2022, 12, 30);

        int daysDifference = (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
        String expectedNights = daysDifference + " night";

        SearchResultPage searchResultPage = homePage.openPage()
                .enterDestination(destination)
                .selectDateRange(dateFrom, dateTo)
                .clickSearchButton();

        int amountOfPages = searchResultPage.getAmountOfPages();

        for (int i = 1; i < amountOfPages; i++) {
            List<SearchResultCard> cards = searchResultPage.getSearchResults();
            for (SearchResultCard card : cards) {
                assertTrue(card.getLocationText().contains(destination));
                assertTrue(card.getNightsText().contains(expectedNights));
            }
            if(i != amountOfPages-1)
                searchResultPage.clickNextPageButton();
        }
    }

    @AfterTest(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
}
