package _11_PageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.BaseDriver;

public class LoginTestElements {

    // LoginTestElements constructor ile sayfadaki elementler initialize edilir.
    public LoginTestElements() {
        PageFactory.initElements(BaseDriver.driver, this);
    }

    // Kullanıcı adı alanı
    @FindBy(id = "user-name")
    public WebElement usernameInput;

    // Şifre alanı
    @FindBy(id = "password")
    public WebElement passwordInput;

    // Giriş butonu
    @FindBy(id = "login-button")
    public WebElement loginButton;

    // Menü butonu (Burger menü)
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;

    // Çıkış butonu
    @FindBy(id = "logout_sidebar_link")
    public WebElement logout;

    // Hata mesajı
    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement errorMessage;

    // Hata mesajı kapatma butonu
    @FindBy(xpath = "//h3[@data-test='error']/button")
    public WebElement errorCloseButton;
}

/**
 * LoginTestElements sınıfı, sayfa üzerindeki tüm elementlerin yerini tutan yapıdır.
 * POM yaklaşımının bir parçası olarak, bu sınıf tüm locators'ı bir araya toplar ve testlerin okunabilirliğini artırır.
 */

