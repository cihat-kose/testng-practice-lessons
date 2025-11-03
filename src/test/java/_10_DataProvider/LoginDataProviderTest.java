package _10_DataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class LoginDataProviderTest extends BaseDriver {

    /**
         Task:
         Kullanıcı Login Testi (Data Provider)
         1) https://www.saucedemo.com/ sitesine gidiniz.
         2) Sitede yer alan geçerli ve geçersiz kullanıcı adı ile login işlemini deneyiniz.
         - Accepted usernames are:
         - standard_user
         - locked_out_user
         - problem_user
         - performance_glitch_user
         - error_user
         - visual_user

         Password for all users: secret_sauce

         3) Geçersiz kullanıcı adı veya şifre ile giriş yapılmaya çalışıldığında hata mesajını doğrulayınız.
         4) Geçerli kullanıcı adı ve şifre ile giriş yaptıktan sonra logout yapınız.
         5) TestNG'deki DataProvider özelliğini kullanarak farklı kullanıcı adları ve şifrelerle bu işlemleri gerçekleştirin.
     */

    @Test(dataProvider = "loginCredentials")
    public void loginWithDataProvider(String username, String password) {
        // 1) https://www.saucedemo.com/ sitesine gidiniz.
        driver.get("https://www.saucedemo.com/");

        // Kullanıcı adı ve şifre alanlarına veri giriyoruz.
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // 2) Geçerli kullanıcı adları ve şifre için başarılı giriş yapmayı kontrol ediyoruz.
        if ((username.equals("standard_user") || username.equals("problem_user")
                || username.equals("performance_glitch_user") || username.equals("error_user")
                || username.equals("visual_user")) && password.equals("secret_sauce")) {

            WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
            burgerMenu.click();

            WebElement logout = driver.findElement(By.id("logout_sidebar_link"));
            logout.click();
        } else if (username.equals("locked_out_user") && password.equals("secret_sauce")) {
            // 4) Kilitlenmiş kullanıcı için hata mesajını kontrol ediyoruz.
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed());

            WebElement errorCloseButton = driver.findElement(By.xpath("//h3[@data-test='error']/button"));
            errorCloseButton.click();
        } else {
            // 3) Geçersiz kullanıcı veya şifre için hata mesajını doğruluyoruz.
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed());

            WebElement errorCloseButton = driver.findElement(By.xpath("//h3[@data-test='error']/button"));
            errorCloseButton.click();
        }
    }

    //  5) TestNG'deki DataProvider özelliğini kullanarak farklı kullanıcı adları ve şifrelerle bu işlemleri gerçekleştirin.
    @DataProvider(name = "loginCredentials")
    public Object[][] loginCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"fake_user_01", "secret_sauce"},
                {"error_user", "fake_password"},
                {"visual_user", "secret_sauce"},
                {"visual_user", "wrong_password"}
        };
    }
}
