package _06_Dependency.methodDependency;

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

    // Rastgele email ve şifre oluşturma


    @Test
    public void registerTest() {
        // 1. Anasayfaya gidin
        driver.get("http://tutorialsninja.com/demo/");

        // 2. "My Account" -> "Register" menüsüne tıklayın


        // 4. Kayıt formunu doldurun


        // 5. "Privacy Policy" kutucuğunu işaretleyin ve "Continue" butonuna tıklayın


        // 6. Kayıt işleminin başarılı olduğunu doğrulayın


        // 7. "Logout" yaparak oturumu kapatın

    }

    @Test(dependsOnMethods = "registerTest")
    public void loginTest() {
        // 1. Anasayfaya geri dönün
        driver.get("http://tutorialsninja.com/demo/");

        // 2. "My Account" -> "Login" menüsüne tıklayın


        // 3. Kayıt sırasında kullandığınız email ve şifre ile giriş yapın


        // 4. Giriş işleminin başarılı olduğunu doğrulayın

    }
}
