package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public class BaseDriver {
    public static WebDriver driver; // SingletonDriver method
    public static WebDriverWait wait;
    public static final Logger logger4j2= LogManager.getLogger();

    @AfterClass
    public static void finishingOperations() {
        Tools.wait(3);
        driver.quit();
        /**
         finishingOperations adında bir metot tanımlanır.
         Bu metot ile Tools sınıfındaki wait metodu çağrılarak 3 saniye bekler ve
         ardından driver.quit() metodu kullanılarak WebDriver kapatılır.
         */
    }

    @BeforeClass
    public void initialOperations() {  // The condition of this is that it is extends and takes place in the first place.
        driver = new EdgeDriver();
        // ChromeDriver nesnesi oluşturulur ve driver değişkenine atanır.

        driver.manage().window().maximize(); // It maximizes the screen.
        /**
         Driver üzerinden manage() metoduyla tarayıcı yönetimi yapılır:

         window().maximize() metodu kullanılarak tarayıcı penceresi tam boyutlu hale getirilir.
         driver üzerinden manage().timeouts() metodu kullanılarak sayfa yükleme süresi ve zımni bekleme süresi ayarlanır.
         */
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // 30 sec delay: time to load the page
        /**
         Sayfa yükleme süresi (pageLoadTimeout) WebDriver'ın bir sayfanın tamamen yüklenmesini beklemesini sağlar.
         Bu süre boyunca WebDriver, sayfanın yüklenmesini bekler ve eğer belirtilen süre içinde sayfa tamamen yüklenmezse bir hata fırlatır.

         Sayfa yükleme süresi, genellikle sayfanın tamamen yüklenmesini beklemek için kullanılır.
         Örneğin, pageLoadTimeout(Duration.ofSeconds(30)) ifadesiyle 30 saniye olarak ayarlandığında,
         WebDriver sayfanın tamamen yüklenmesini 30 saniye boyunca bekler.
         Eğer sayfa belirtilen süre içinde yüklenmezse, bir TimeoutException hatası fırlatılır.

         Bu süre, sayfanın tamamen yüklenmesi için yeterli bir süre olarak ayarlanmalıdır.
         Sayfanın içeriği, resimleri, stil dosyalarını ve diğer kaynakları tamamen yüklemesi beklenir.
         */

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));  // 30 sec delay: time to find the element
        /**
         Zımni bekleme süresi (implicitlyWait) WebDriver'ın belirli bir süre boyunca arama işlemlerini beklemesini sağlar.
         Bu süre boyunca WebDriver, sayfadaki elemanları bulmak için bekler.
         Eğer eleman hemen bulunamazsa, WebDriver belirtilen süre boyunca arama işlemini tekrar eder.

         Bu süre, WebDriver'ın elemanları bulmak için beklerken geçen süreyi temsil eder.
         Örneğin, implicitlyWait(Duration.ofSeconds(30)) ifadesiyle 30 saniye olarak ayarlandığında,
         WebDriver elemanları bulmak için 30 saniye boyunca bekler.

         Zımni bekleme süresi, elemanların sayfa yüklendikten hemen sonra görünmediği durumlarda kullanışlı olabilir.
         Bu süreyi, elemanların sayfada görünmesini beklemek için yeterli bir süre olarak ayarlayabilirsiniz.
         */

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @BeforeMethod
    public void beforeMethod() {
        logger4j2.info("Test method has started");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        logger4j2.info(result.getName() + " has been executed successfully-->" + (result.getStatus() == 1 ? "Passed" : "Failed"));
    }
}

