package _06_Dependency.groupDependency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

import java.util.Random;

public class TutorialsNinjaGroupDependencyTest extends BaseDriver {

    /**
     Task: Kullanıcı Kayıt, Giriş ve Hesap Güncelleme İşlemi (Grup Bağımlılığı)

     Senaryo 1: auth Grubu (Kayıt ve Giriş İşlemi)

     1. Anasayfaya gidin: http://tutorialsninja.com/demo/
     2. "My Account" menüsüne tıklayın ve "Register" bağlantısına tıklayın.
     3. Kayıt formunda şu alanları doldurun:
     - First Name, Last Name, Email, Telephone, Password, Confirm Password.
     4. "Privacy Policy" kutucuğunu işaretleyin ve "Continue" butonuna tıklayın.
     5. Kayıt işleminin başarılı olduğunu doğrulayın (Başarı mesajını kontrol edin).
     6. Oturumun kapatıldığını doğrulamak için "Logout" yapın.

     Senaryo 2: auth Grubu (Login İşlemi)

     1. Anasayfaya gidin ve "My Account" menüsüne tıklayıp "Login" bağlantısına tıklayın.
     2. Kayıt sırasında oluşturduğunuz email ve şifre ile giriş yapın.
     3. Giriş işleminin başarılı olduğunu doğrulayın (Logout bağlantısının göründüğünü kontrol edin).

     Senaryo 3: accountOperations Grubu (Hesap Güncelleme ve Çıkış İşlemi)

     1. Hesap bilgilerini güncelleyin (First Name'i "UpdatedName" olarak değiştirin).
     2. Hesap bilgilerini güncelledikten sonra başarılı olduğunu doğrulayın.
     3. Çıkış işlemi yaparak oturumun kapatıldığını doğrulayın.
     */

    // Random objesi
    Random random = new Random();

    // Rastgele email ve şifre oluşturma
    String generatedEmail = "user_" + random.nextInt(10000) + "@testmail.com";
    String generatedPassword = "password" + random.nextInt(10000);

    @Test(groups = "auth")
    public void registerTest() {
        // Anasayfaya gidin
        driver.get("http://tutorialsninja.com/demo/");

        // "My Account" -> "Register" menüsüne tıklayın
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement registerLink = driver.findElement(By.linkText("Register"));
        registerLink.click();

        // Kayıt formunu doldurun
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

        WebElement privacyPolicyCheckbox = driver.findElement(By.name("agree"));
        privacyPolicyCheckbox.click();
        WebElement continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        continueButton.click();

        // Kayıt işleminin başarılı olduğunu doğrulayın
        wait.until(ExpectedConditions.titleIs("Your Account Has Been Created!"));
        WebElement successMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertTrue(successMessage.getText().contains("Your Account Has Been Created!"), "Kayıt işlemi başarısız oldu!");

        // Logout yapın
        WebElement myAccountDropdown = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountDropdown.click();
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();
    }

    @Test(dependsOnMethods = "registerTest", groups = "auth")
    public void loginTest() {
        // Anasayfaya gidin
        driver.get("http://tutorialsninja.com/demo/");

        // "My Account" -> "Login" menüsüne tıklayın
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        // Kayıt sırasında oluşturduğunuz email ve şifre ile giriş yapın
        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(generatedEmail);

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(generatedPassword);

        WebElement loginButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        loginButton.click();

        // Giriş yapıldığını doğrulayın
        wait.until(ExpectedConditions.titleIs("My Account"));
        WebElement logoutText = driver.findElement(By.xpath("//*[@id='column-right']/div/a[13]"));
        Assert.assertEquals(logoutText.getText(), "Logout", "Login işlemi başarısız oldu!");
    }

    @Test(dependsOnGroups = "auth", groups = "accountOperations")
    public void updateAccountTest() {
        // Hesap bilgilerini güncelleyin
        WebElement accountDetailsLink = driver.findElement(By.linkText("Edit Account"));
        accountDetailsLink.click();

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.clear();
        firstNameInput.sendKeys("UpdatedName");

        WebElement continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        continueButton.click();

        WebElement successMessage = driver.findElement(By.cssSelector("div.alert.alert-success"));
        Assert.assertTrue(successMessage.getText().contains("Your account has been successfully updated."));
    }

    @Test(dependsOnMethods = "updateAccountTest", groups = "accountOperations")
    public void logoutTest() {
        // Çıkış işlemini yapın
        WebElement myAccountMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
        myAccountMenu.click();

        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();

        WebElement logoutMessage = driver.findElement(By.cssSelector("div#content h1"));
        Assert.assertTrue(logoutMessage.getText().contains("Account Logout"));
    }
}
