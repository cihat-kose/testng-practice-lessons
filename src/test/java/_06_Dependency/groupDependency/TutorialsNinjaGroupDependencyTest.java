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

        // Kayıt formunu doldurun


        // Kayıt işleminin başarılı olduğunu doğrulayın


        // Logout yapın

    }

    @Test(dependsOnMethods = "registerTest", groups = "auth")
    public void loginTest() {
        // Anasayfaya gidin
        driver.get("http://tutorialsninja.com/demo/");

        // "My Account" -> "Login" menüsüne tıklayın


        // Kayıt sırasında oluşturduğunuz email ve şifre ile giriş yapın


        // Giriş yapıldığını doğrulayın

    }

    @Test(dependsOnGroups = "auth", groups = "accountOperations")
    public void updateAccountTest() {
        // Hesap bilgilerini güncelleyin

    }

    @Test(dependsOnMethods = "updateAccountTest", groups = "accountOperations")
    public void logoutTest() {
        // Çıkış işlemini yapın

    }
}
