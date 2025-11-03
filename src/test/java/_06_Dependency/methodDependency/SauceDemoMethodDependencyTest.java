package _06_Dependency.methodDependency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class SauceDemoMethodDependencyTest extends BaseDriver {

    /**
        Task:
        Kullanıcı Girişi, Ürün Ekleme ve Çıkış İşlemi

        Senaryo 1: Kullanıcı Girişi
        1. Swag Labs anasayfasına gidin: https://www.saucedemo.com/
        2. Giriş formunda Kullanıcı Adı ve Şifre alanlarını doldurun:
           - Kullanıcı adı: "standard_user"
           - Şifre: "secret_sauce"
        3. "Login" butonuna tıklayın.
        4. Giriş işleminin başarılı olduğunu doğrulayın:
           - Sayfada "Swag Labs" başlığı görünmelidir.

        Senaryo 2: Ürün Sepete Ekleme
        1. Giriş yaptıktan sonra bir ürünü (örneğin, "Sauce Labs Backpack") sepete ekleyin.
        2. Sepet simgesine tıklayın.
        3. Ürünün sepette olduğunu doğrulayın.

        Senaryo 3: Kullanıcı Çıkış İşlemi
        1. Giriş yaptıktan sonra menüden çıkış yapın.
        2. Çıkış işleminin başarılı olduğunu doğrulayın:
           - Login butonunun tekrar görünmesi gerekmektedir.
     */

    // Kullanıcı adı ve şifre (Swag Labs sitesine giriş yapmak için gerekli geçerli bilgiler)
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";

    @Test
    public void loginTest() {
        // 1. Swag Labs anasayfasına gidin
        driver.get("https://www.saucedemo.com/");

        // Açıklama: Swag Labs anasayfası, giriş işlemi için kullanıcı adı ve şifre gerektirir.
        // Kullanıcı adı ve şifre alanlarını dolduruyoruz.

        // 2. Kullanıcı adı ve şifre alanlarını doldurun
        WebElement usernameInput = driver.findElement(By.id("user-name")); // Kullanıcı adı alanı bulunuyor
        usernameInput.sendKeys(validUsername); // Geçerli kullanıcı adı alanına yazılıyor

        WebElement passwordInput = driver.findElement(By.id("password")); // Şifre alanı bulunuyor
        passwordInput.sendKeys(validPassword); // Geçerli şifre alanına yazılıyor

        // 3. "Login" butonuna tıklayın
        WebElement loginButton = driver.findElement(By.id("login-button")); // Giriş butonu bulunuyor
        loginButton.click(); // Giriş butonuna tıklanıyor

        // 4. Giriş işleminin başarılı olduğunu doğrulayın
        wait.until(ExpectedConditions.titleIs("Swag Labs")); // Sayfanın başlığının "Swag Labs" olduğundan emin olun
        WebElement productsTitle = driver.findElement(By.className("title")); // Sayfa başlığı bulunuyor
        Assert.assertTrue(productsTitle.isDisplayed(), "Login işlemi başarısız oldu!"); // Sayfa başlığının görüntülenip görüntülenmediği kontrol ediliyor
    }

    /**
        Bu metod, başarılı bir giriş işlemine bağlıdır.
        TestNG'nin "dependsOnMethods" özelliği sayesinde, bu metod yalnızca "loginTest" başarılı olursa çalıştırılır.
        Bu, testlerin belirli bir sıraya göre çalışmasını ve kritik adımların atlanmamasını sağlar.
     */
    @Test(dependsOnMethods = "loginTest")
    public void addProductToCartTest() {
        // Açıklama: Giriş yaptıktan sonra, bir ürünü sepete ekleme işlemi yapılıyor.

        // Ürünü sepete ekleyin
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack")); // "Sauce Labs Backpack" ürünü bulunuyor
        addToCartButton.click(); // Ürün sepete ekleniyor

        // Sepet simgesine tıklayın
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link")); // Sepet simgesi bulunuyor
        cartIcon.click(); // Sepet simgesine tıklanarak sepete gidiliyor

        // Ürünün sepette olduğunu doğrulayın
        WebElement cartItem = driver.findElement(By.className("inventory_item_name")); // Sepetteki ürün ismi bulunuyor
        Assert.assertTrue(cartItem.getText().contains("Sauce Labs Backpack"), "Ürün sepete eklenemedi!"); // Ürünün sepette olup olmadığı doğrulanıyor
    }

    /**
        Bu metod, başarılı bir şekilde ürün sepete eklendikten sonra çalışır.
        "addProductToCartTest" başarılı olmadan bu metod çalıştırılmaz.
        Yani bu test "dependsOnMethods" ile önceki adımlara bağlıdır ve sıralı bir işlem akışı sağlar.
     */
    @Test(dependsOnMethods = "addProductToCartTest")
    public void logoutTest() {
        // Açıklama: Ürün sepete eklendikten sonra, menüden çıkış işlemi yapılır.

        // Menüden çıkış yapın
        WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn")); // Menü simgesi (Burger Menüsü) bulunuyor
        burgerMenu.click(); // Menü açılıyor

        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link")); // Çıkış (Logout) bağlantısı bulunuyor
        logoutLink.click(); // Çıkış yapılıyor

        // Çıkış işleminin başarılı olduğunu doğrulayın
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))); // Login butonunun göründüğünden emin olun
        WebElement loginButton = driver.findElement(By.id("login-button")); // Login butonu bulunuyor
        Assert.assertTrue(loginButton.isDisplayed(), "Logout işlemi başarısız oldu!"); // Login butonunun görünüp görünmediği kontrol ediliyor
    }
}
