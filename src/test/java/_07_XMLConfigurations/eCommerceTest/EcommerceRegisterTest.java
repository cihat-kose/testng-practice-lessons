package _07_XMLConfigurations.eCommerceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class EcommerceRegisterTest extends BaseDriver {

    /**
     Task:
     Kullanıcı Kayıt İşlemi

     1. Anasayfaya gidin: http://tutorialsninja.com/demo/
     2. "My Account" menüsüne tıklayın ve "Register" bağlantısına tıklayın.
     3. Kayıt formunda gerekli alanları doldurun (First Name, Last Name, Telephone vb.).
     4. Email alanı dinamik olarak oluşturulacaktır ve Password XML'den parametre olarak alınacaktır.
     5. "First Name", "Last Name", "Telephone" ve "Password" alanlarına XML'den gelen değerler girilecektir.
     6. "Privacy Policy" kutucuğunu işaretleyin ve "Continue" butonuna tıklayın.
     7. Kayıt işleminin başarılı olduğunu doğrulayın:
     Sayfada "Your Account Has Been Created!" mesajını görmelisiniz.
     8. "Logout" yaparak oturumu kapatın.

     Test, aşağıdaki parametreleri XML'den alacaktır:
     - firstName: Kayıt formunda kullanıcının ismi.
     - lastName: Kayıt formunda kullanıcının soyismi.
     - telephone: Kayıt formunda kullanıcının telefon numarası.
     - password: Kayıt formunda kullanıcının şifresi.
     */

    // Dinamik email, login testi için kullanılacak. Dinamik email oluşturma sebebimiz,
    // aynı email ile birden fazla kez kayıt yapmaya çalışıldığında hata alma durumunu ortadan kaldırmaktır.
    public static String dynamicEmail;

    @Test
    @Parameters({"firstName", "lastName", "telephone", "password"})
    public void registerTest(String firstName, String lastName, String telephone, String password) {
        // Test başladığında anasayfaya gidin
        driver.get("http://tutorialsninja.com/demo/");

        // Dinamik email oluşturma: Aynı email ile birden fazla kez kayıt yapılmasını engellemek için
        dynamicEmail = "testuser_" + System.currentTimeMillis() + "@example.com";

        // "My Account" -> "Register" menüsüne tıklayın
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        // Kayıt formunda gerekli alanları doldurun
        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys(firstName);  // XML'den alınan firstName

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys(lastName);  // XML'den alınan lastName

        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(dynamicEmail);  // Dinamik olarak oluşturulan email

        WebElement telephoneInput = driver.findElement(By.id("input-telephone"));
        telephoneInput.sendKeys(telephone);  // XML'den alınan telefon numarası

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(password);  // XML'den alınan password

        WebElement confirmPasswordInput = driver.findElement(By.id("input-confirm"));
        confirmPasswordInput.sendKeys(password);  // Password doğrulama

        // "Privacy Policy" kutucuğunu işaretleyin ve "Continue" butonuna tıklayın
        WebElement privacyPolicyCheckbox = driver.findElement(By.name("agree"));
        privacyPolicyCheckbox.click();

        WebElement continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        continueButton.click();

        // Kayıt işleminin başarılı olduğunu doğrulayın
        wait.until(ExpectedConditions.titleIs("Your Account Has Been Created!"));
        WebElement successMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertTrue(successMessage.getText().contains("Your Account Has Been Created!"), "Kayıt işlemi başarısız oldu!");

        // "Logout" yaparak oturumu kapatın
        WebElement myAccountDropdown = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountDropdown.click();

        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();
    }
}
