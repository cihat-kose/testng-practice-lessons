package _11_PageObjectModel;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class LoginTestPOM extends BaseDriver {

    /**
     * Task:
     * Kullanıcı Login Testi (Data Provider)
     * 1) https://www.saucedemo.com/ sitesine gidiniz.
     * 2) Sitede yer alan geçerli ve geçersiz kullanıcı adı ile login işlemini deneyiniz.
     * - Accepted usernames are:
     * - standard_user
     * - locked_out_user
     * - problem_user
     * - performance_glitch_user
     * - error_user
     * - visual_user
     * <p>
     * Password for all users: secret_sauce
     * <p>
     * 3) Geçersiz kullanıcı adı veya şifre ile giriş yapılmaya çalışıldığında hata mesajını doğrulayınız.
     * 4) Geçerli kullanıcı adı ve şifre ile giriş yaptıktan sonra logout yapınız.
     * 5) TestNG'deki DataProvider özelliğini kullanarak farklı kullanıcı adları ve şifrelerle bu işlemleri gerçekleştirin.
     */

    @Test(dataProvider = "loginCredentials")
    public void loginWithDataProvider(String username, String password) {
        // LoginTest_Elements sınıfından bir örnek oluşturulur
        LoginTestElements loginTestElements =new LoginTestElements();

        // 1) https://www.saucedemo.com/ sitesine gidiniz.
        driver.get("https://www.saucedemo.com/");

        // Kullanıcı adı ve şifre alanlarına veri giriyoruz.
        loginTestElements.usernameInput.sendKeys(username);
        loginTestElements.passwordInput.sendKeys(password);
        loginTestElements.loginButton.click();

        // 2) Geçerli kullanıcı adları ve şifre için başarılı giriş yapmayı kontrol ediyoruz.
        if ((username.equals("standard_user") || username.equals("problem_user")
                || username.equals("performance_glitch_user") || username.equals("error_user")
                || username.equals("visual_user")) && password.equals("secret_sauce")) {

            loginTestElements.burgerMenu.click();
            loginTestElements.logout.click();
        } else if (username.equals("locked_out_user") && password.equals("secret_sauce")) {
            // 4) Kilitlenmiş kullanıcı için hata mesajını kontrol ediyoruz.
            Assert.assertTrue(loginTestElements.errorMessage.isDisplayed());
            loginTestElements.errorCloseButton.click();
        } else {
            // 3) Geçersiz kullanıcı veya şifre için hata mesajını doğruluyoruz.
            Assert.assertTrue(loginTestElements.errorMessage.isDisplayed());
            loginTestElements.errorCloseButton.click();
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
