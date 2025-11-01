package _05_Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.BaseDriver;

public class DuckDuckGoHardAndSoftAssertions extends BaseDriver {
    /**
     * DuckDuckGo Üzerinde Hard ve Soft Assert Kullanımı (TestNG araması)
     *
     * Görev:
     * 1. "https://duckduckgo.com/" sitesine gidin.
     * 2. URL'in "duckduckgo.com" içerdiğini doğrulayın.
     * 3. Sayfa başlığının "DuckDuckGo" içerdiğini doğrulayın.
     * 4. Arama kutusuna "TestNG" yazın ve Enter'a basın.
     * 5. Arama sonuçları arasında ilk görünen başlığın "TestNG" olduğunu doğrulayın.
     */

    @Test
    public void hardAssertTesting(){
        driver.get("https://duckduckgo.com");

        // 1. URL doğrulaması
        Assert.assertTrue(driver.getCurrentUrl().contains("duckduckgo.com"),"URL doğrulama hatası");

        // 2. Sayfa başlığı doğrulaması
        Assert.assertTrue(driver.getTitle().contains("DuckDuckGo"), "Title dogrulama hatasi");

        // 3. Arama işlemi
        WebElement searchInput=wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys("TestNG" + Keys.ENTER);

        // 4. İlk başlığı yakala ve doğrula

        // 4. İlk başlığı yakala ve doğrula
        WebElement firstTitle=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Ee2e63EzQ9F3xq9wsGDY")));

        Assert.assertEquals(firstTitle.getText().trim(),"TestNG","İlk sonuç başlığı beklenenden farklı!");

    }

    @Test
    public void softAssertTesting(){
        driver.get("https://duckduckgo.com");

        SoftAssert softAssert=new SoftAssert();

        // 1. URL doğrulaması (Bilincli olarak hatali yazdik)
        softAssert.assertTrue(driver.getCurrentUrl().contains("duckduckgo.net"),"URL doğrulama hatası");

        // 2. Sayfa başlığı doğrulaması
        softAssert.assertTrue( driver.getTitle().contains("duckduckgo"),"Title doğrulama hatası");

        // 3. Arama işlemi
        WebElement searchInput= wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys("TestNG" + Keys.ENTER);

        // 4. İlk başlığı yakala ve doğrula
        WebElement firstTitle=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Ee2e63EzQ9F3xq9wsGDY")));

        softAssert.assertEquals(firstTitle.getText().trim(),"TestNG","İlk sonuç başlığı beklenenden farklı!");

        System.out.println("Tüm soft assert kontrolleri tamamlandı. Sonuçlar şimdi değerlendiriliyor.");
        softAssert.assertAll();
    }
}
