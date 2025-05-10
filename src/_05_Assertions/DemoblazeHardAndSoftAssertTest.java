package _05_Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.BaseDriver;

public class DemoblazeHardAndSoftAssertTest extends BaseDriver {

    /**
     * Task:
     * https://www.demoblaze.com/ sitesi kullanılarak Hard ve Soft Assert farkını gösteren testler yazınız.
     *
     * 1- Siteye gidilir.
     * 2- Başlık "STORE" içeriyor mu kontrol edilir.
     * 3- "Phones" kategorisine tıklanır.
     * 4- İlk ürün ismi "Samsung galaxy s6" içeriyor mu kontrol edilir.
     */

    @Test
    public void hardAssertTest() {
        driver.get("https://www.demoblaze.com/");

        // Hard Assert: Başlık kontrolü
        Assert.assertTrue(driver.getTitle().contains("STORE"), "Sayfa başlığı 'STORE' içermiyor!");

        // Kategoriye tıklama
        WebElement phonesCategory = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones")));
        phonesCategory.click();

        // İlk ürün kontrolü
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid .card-title")));
        String productName = firstProduct.getText().toLowerCase();
        Assert.assertTrue(productName.contains("samsung"), "İlk ürün 'Samsung' içermiyor!");
    }

    @Test
    public void softAssertTest() {
        driver.get("https://www.demoblaze.com/");

        SoftAssert softAssert = new SoftAssert();

        // Soft Assert: Başlık kontrolü
        softAssert.assertTrue(driver.getTitle().contains("STORE"), "Sayfa başlığı 'STORE' içermiyor!");

        // Kategoriye tıklama
        WebElement phonesCategory = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones")));
        phonesCategory.click();

        // İlk ürün kontrolü
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid .card-title")));
        String productName = firstProduct.getText().toLowerCase();
        softAssert.assertTrue(productName.contains("samsung"), "İlk ürün 'Samsung' içermiyor!");

        // Ek hata örneği (bilerek hata verdirme)
        softAssert.assertTrue(productName.contains("iphone"), "İlk ürün 'iPhone' içermiyor!");

        System.out.println("Soft Assert ile test devam etti, assertAll() sonrası hatalar raporlanacak.");
        softAssert.assertAll();
    }
}
