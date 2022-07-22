import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

import java.time.LocalDate;

public class TestSearch {

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

    @Test
    public void Test() throws InterruptedException {
        SearchResultPage searchResultPage = homePage.openPage()
                .enterDestination("New York")
                .selectDate(LocalDate.of(2022,12,1),
                            LocalDate.of(2022,12,30))
                .clickSearchButton();


        Thread.sleep(5000);
    }

    @AfterTest(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
}
