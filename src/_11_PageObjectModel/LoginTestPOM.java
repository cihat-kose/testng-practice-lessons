package _11_PageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class LoginTestPOM extends BaseDriver {

    // Test senaryosunu yürütmek için kullanılacak test metodu
    @Test(dataProvider = "credentials")
    public void loginTest(String username, String password){

        // LoginTest_Elements sınıfından bir örnek oluşturulur
        LoginTestElements loginTestElements = new LoginTestElements();

        // Web sitesine gidilir
        driver.get("https://www.saucedemo.com/");

        // Kullanıcı adı ve şifre alanları doldurulur
        loginTestElements.usernameInput.sendKeys(username);
        loginTestElements.passwordInput.sendKeys(password);
        loginTestElements.loginButton.click();

        // Kullanıcı adı ve şifre doğruysa
        if (isUserValid(username) && password.equals("secret_sauce")) {
            // Burger menüsüne tıklanır ve çıkış yapılır
            waitForElementVisible(loginTestElements.burgerMenu);
            loginTestElements.burgerMenu.click();
            loginTestElements.logout.click();
        }
        // Kullanıcı adı "locked_out_user" ve şifre "secret_sauce" ise
        else if (username.equals("locked_out_user") && password.equals("secret_sauce")) {
            verifyErrorMessage(loginTestElements);
        }
        // Diğer durumlarda
        else {
            verifyErrorMessage(loginTestElements);
        }
    }

    // Kullanıcının geçerli olup olmadığını kontrol eden metot
    private boolean isUserValid(String username) {
        return username.equals("standard_user") ||
                username.equals("problem_user") ||
                username.equals("performance_glitch_user") ||
                username.equals("error_user") ||
                username.equals("visual_user");
    }

    // Hata mesajını doğrulayan metot
    private void verifyErrorMessage(LoginTestElements loginTestElements) {
        waitForElementVisible(loginTestElements.errorMessage);
        Assert.assertTrue(loginTestElements.errorMessage.isDisplayed(), "Hata mesajı görüntülenmedi!");
        loginTestElements.errorCloseButton.click();
        loginTestElements.usernameInput.clear();
        loginTestElements.passwordInput.clear();
    }

    // WebElement görünür olana kadar bekleyen metot
    private void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Test senaryosunda kullanılacak verileri sağlayan veri sağlayıcısı
    @DataProvider
    public Object[][] credentials() {
        // Kullanıcı adı ve şifre kombinasyonlarını içeren bir dizi
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"fake_user_01", "secret_sauce"},
                {"error_user", "fake_password_01"},
                {"performance_glitch_user", "secret_sauce"},
                {"fake_user_02", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "fake_password_01"},
                {"visual_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
        };
    }
}
