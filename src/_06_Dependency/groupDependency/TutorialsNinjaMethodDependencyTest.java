package _06_Dependency.groupDependency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

import java.util.Random;

public class TutorialsNinjaMethodDependencyTest extends BaseDriver {

    /**
     Task: Kullanıcı Kayıt ve Login İşlemi (Yöntem Bağımlılığı)

     Senaryo 1: Kullanıcı Kayıt İşlemi

     1. Anasayfaya gidin: http://tutorialsninja.com/demo/
     2. "My Account" menüsüne tıklayın.
     3. Açılan menüden "Register" bağlantısına tıklayın.
     4. Kayıt formunda şu alanları doldurun:
     - First Name, Last Name, Email, Telephone, Password, Confirm Password
     5. "Privacy Policy" kutucuğunu işaretleyin ve "Continue" butonuna tıklayın.
     6. Kayıt işleminin başarılı olduğunu doğrulayın (Başarı mesajını kontrol edin).
     7. "Logout" yaparak oturumu kapatın.

     Senaryo 2: Kullanıcı Giriş İşlemi

     1. Anasayfaya geri dönün.
     2. "My Account" menüsüne tıklayın ve "Login" bağlantısına tıklayın.
     3. Kayıt sırasında kullandığınız email ve şifre ile giriş yapın.
     4. Giriş işleminin başarılı olduğunu doğrulayın (Logout butonunun görünür olduğunu kontrol edin).
     */

    // Random objesi
    Random random = new Random();

    // Rastgele email ve şifre oluşturma
    String generatedEmail = "user_" + random.nextInt(10000) + "@testmail.com";
    String generatedPassword = "password" + random.nextInt(10000);

    @Test
    public void registerTest() {
        // 1. Anasayfaya gidin
        driver.get("http://tutorialsninja.com/demo/");

        // 2. "My Account" -> "Register" menüsüne tıklayın
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        // 4. Kayıt formunu doldurun
        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys("Kerem");

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys("Mert");

        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(generatedEmail);

        WebElement telephoneInput = driver.findElement(By.id("input-telephone"));
        telephoneInput.sendKeys("15551234567");

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(generatedPassword);

        WebElement confirmPasswordInput = driver.findElement(By.id("input-confirm"));
        confirmPasswordInput.sendKeys(generatedPassword);

        // 5. "Privacy Policy" kutucuğunu işaretleyin ve "Continue" butonuna tıklayın
        WebElement privacyPolicyCheckbox = driver.findElement(By.name("agree"));
        privacyPolicyCheckbox.click();

        WebElement continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        continueButton.click();

        // 6. Kayıt işleminin başarılı olduğunu doğrulayın
        wait.until(ExpectedConditions.titleIs("Your Account Has Been Created!"));
        WebElement successMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertTrue(successMessage.getText().contains("Your Account Has Been Created!"), "Kayıt işlemi başarısız oldu!");

        // 7. "Logout" yaparak oturumu kapatın
        WebElement myAccountDropdown = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountDropdown.click();

        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();
    }

    @Test(dependsOnMethods = "registerTest")
    public void loginTest() {
        // 1. Anasayfaya geri dönün
        driver.get("http://tutorialsninja.com/demo/");

        // 2. "My Account" -> "Login" menüsüne tıklayın
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        // 3. Kayıt sırasında kullandığınız email ve şifre ile giriş yapın
        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(generatedEmail);

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(generatedPassword);

        WebElement loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        loginButton.click();

        // 4. Giriş işleminin başarılı olduğunu doğrulayın
        wait.until(ExpectedConditions.titleIs("My Account"));
        WebElement logoutText = driver.findElement(By.xpath("//*[@id='column-right']/div/a[13]"));
        Assert.assertEquals(logoutText.getText(), "Logout", "Login işlemi başarısız oldu!");
    }
}
