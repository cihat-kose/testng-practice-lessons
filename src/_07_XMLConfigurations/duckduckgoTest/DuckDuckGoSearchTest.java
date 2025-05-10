package _07_XMLConfigurations.duckduckgoTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class DuckDuckGoSearchTest extends BaseDriver {
    /**
        Task:
        DuckDuckGo Üzerinde Arama Yapma (XML Parametreli)

        1. XML dosyasından gelen URL'ye gidin (https://www.duckduckgo.com).
        2. Başlığın "DuckDuckGo" içerdiğini doğrulayın.
        3. XML'den gelen arama terimini arama kutusuna yazın ve arama yapın.
        4. Arama sonuçlarının görüntülendiğini doğrulayın.
     */

    @Test
    @Parameters({"url", "searchTerm"})
    public void duckDuckGoSearch(String url, String searchTerm) {
        driver.get(url);

        // 1. URL doğrulaması
        Assert.assertTrue(driver.getCurrentUrl().contains("duckduckgo.com"), "URL doğrulama hatası");

        // 2. Sayfa başlığı doğrulaması
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("duckduckgo"), "Title doğrulama hatası");

        // 3. Arama işlemi
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys(searchTerm + Keys.ENTER);

        // 4. Arama sonucu kutusu kontrolü
        WebElement resultsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".react-results--main")));
        Assert.assertTrue(resultsContainer.isDisplayed(), "Arama sonuçları görünmedi!");
    }
}
