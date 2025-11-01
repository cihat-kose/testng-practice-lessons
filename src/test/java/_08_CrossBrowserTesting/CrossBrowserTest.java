package _08_CrossBrowserTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriverParameter;

public class CrossBrowserTest extends BaseDriverParameter {
    /**
        Task: Cross Browser Testing - DuckDuckGo Arama Testi

        1. DuckDuckGo ana sayfasına gidin.
        2. Başlığı doğrulayın.
        3. Arama kutusuna "Cross Browser Testing" yazın ve arama yapın.
        4. Arama sonuçlarının yüklendiğini doğrulayın.
     */

    @Test
    public void crossBrowserTest(){
        // 1. DuckDuckGo'ya gidin
        driver.get("https://www.duckduckgo.com");

        // 2. Sayfa başlığının DuckDuckGo içerdiğini doğrula
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("DuckDuckGo"), "Başlık bekleneni içermiyor!");

        // 3. Arama kutusuna yaz
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys("Cross Browser Testing"+ Keys.ENTER);

        // 4. Arama sonuçlarının geldiğini doğrula
        WebElement resultsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".react-results--main")));

        Assert.assertTrue(resultsContainer.isDisplayed(), "Arama sonuçları görünmedi!");
    }
}
