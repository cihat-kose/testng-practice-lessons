package _07_XMLConfigurations.eCommerceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class EcommerceLoginTest extends BaseDriver {

    /**
        Task: Kullanıcı Giriş İşlemi

        1. Anasayfaya gidin ve "My Account" menüsüne tıklayıp "Login" bağlantısına tıklayın.
        2. Email ve Password alanlarını doldurun:
           - Email: XML'den parametre olarak alınacak (testuser123@example.com).
           - Password: XML'den parametre olarak alınacak (TestPassword123).
        3. Giriş işleminin başarılı olduğunu doğrulayın: Sayfada "Logout" bağlantısının göründüğünü kontrol edin.
     */

    @Test
    @Parameters({"password"})
    public void loginTest(String password) {
        // Anasayfaya gidin
        driver.get("http://tutorialsninja.com/demo/");

        // "My Account" -> "Login" menüsüne tıklayın
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        // Giriş formunda Email ve Password alanlarını doldurun
        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(EcommerceRegisterTest.dynamicEmail);  // Dinamik email, RegisterTest'te oluşturulup kullanılıyor

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(password);  // XML'den gelen password

        WebElement loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        loginButton.click();

        // Giriş işleminin başarılı olduğunu doğrulayın
        wait.until(ExpectedConditions.titleIs("My Account"));
        WebElement logoutText = driver.findElement(By.linkText("Logout"));
        Assert.assertEquals(logoutText.getText(), "Logout", "Login işlemi başarısız oldu!");
    }
}
