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

        // 2. Sayfa başlığı doğrulaması

        // 3. Arama işlemi


        // 4. İlk başlığı yakala ve doğrula

    }

    @Test
    public void softAssertTesting(){
        driver.get("https://duckduckgo.com");



        // 1. URL doğrulaması (Bilincli olarak hatali yazdik)

        // 2. Sayfa başlığı doğrulaması

        // 3. Arama işlemi


        // 4. İlk başlığı yakala ve doğrula


    }
}
