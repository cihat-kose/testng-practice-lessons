package _05_Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGAssertions {

    // TestNG Assertion'ları kullanarak bazı doğrulama işlemleri yapıyoruz.

    @Test(priority = 0)  // İlk test, priority verilmediği için bu metot ilk çalışır.
    public void testEqualProgrammingLanguageText() {
        // İki metin karşılaştırılıyor: İçerik ve referans kontrolü.
        String actualText = "Java is a programming language.";
        String expectedText = "Java is a programming language.";

        // Assert.assertSame() iki nesnenin aynı hafıza adresine sahip olup olmadığını kontrol eder.
        Assert.assertSame(actualText, expectedText, "Metinlerin hafıza adresleri aynı değil.");
        // Assert.assertEquals() iki nesnenin içeriğinin aynı olup olmadığını kontrol eder.
        Assert.assertEquals(actualText, expectedText, "Metin içerikleri eşit değil.");

        System.out.println("Equal Strings testi başarıyla geçti.");
    }

    @Test(priority = 1)  // Priority = 1 olduğu için bu test ikinci sırada çalışır.
    public void testSameGreetingText() {
        // İki metin karşılaştırılıyor: Aynı referansa sahip olup olmadıkları kontrol ediliyor.
        String greetingText1 = "Hello!";
        String greetingText2 = "Hello!";

        Assert.assertSame(greetingText1, greetingText2, "Metinlerin hafıza adresleri aynı değil.");
        System.out.println("Same Strings testi başarıyla geçti.");
    }

    @Test(priority = 2)  // Priority = 2 olduğu için bu test üçüncü sırada çalışır.
    public void testEqualProgrammingLanguage() {
        // İki metin karşılaştırılıyor: İçerik kontrolü.
        String programmingLanguage1 = "Java";
        String programmingLanguage2 = "Java";

        Assert.assertEquals(programmingLanguage1, programmingLanguage2, "Metin içerikleri eşit değil.");
        System.out.println("Equal Content testi başarıyla geçti.");
    }

    @Test(priority = 3)  // Priority = 3 olduğu için bu test en son çalışır.
    public void testSameBrowserName() {
        // İki metin karşılaştırılıyor: Aynı hafıza referansına sahip olup olmadıkları kontrol ediliyor.
        String browserName1 = "Selenium";
        String browserName2 = "Selenium";

        Assert.assertSame(browserName1, browserName2, "Metinlerin hafıza adresleri aynı değil.");
        System.out.println("Same Memory Reference testi başarıyla geçti.");
    }
}
