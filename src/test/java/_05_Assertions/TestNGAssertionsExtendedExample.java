package _05_Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertionsExtendedExample {

    @Test
    public void hardAssertTest() {
        System.out.println("Hard Assert Test başlıyor...");

        String expectedTitle = "Google";
        String actualTitle = "Google";

        // 1. Başlıkların eşit olup olmadığını kontrol et
        Assert.assertEquals(actualTitle, expectedTitle, "Başlıklar eşit değil!");

        // 2. Koşulun doğru olup olmadığını kontrol et
        Assert.assertTrue(actualTitle.contains("Google"), "Başlık Google içermiyor!");

        System.out.println("Bu satır sadece assert başarılı olursa çalışır.");
    }

    @Test
    public void multipleHardAssertTest() {
        System.out.println("Birden fazla Hard Assert Test başlıyor...");

        // İlk assertion başarılı olacak
        int expectedNumber = 5;
        int actualNumber = 5;
        Assert.assertEquals(actualNumber, expectedNumber, "Sayılar eşit değil!");

        // İkinci assertion başarısız olacak ve test burada duracak
        String expectedText = "Hello World";
        String actualText = "Hello TestNG";
        Assert.assertEquals(actualText, expectedText, "Metinler eşit değil!");

        // Bu satır asla çalışmayacak çünkü yukarıdaki assertion başarısız oldu
        System.out.println("Bu satır, assertion başarılı olursa çalışır.");
    }

    @Test
    public void softAssertTest() {
        System.out.println("Soft Assert Test başlıyor...");

        SoftAssert softAssert = new SoftAssert();

        // 1. Başlıkların eşit olup olmadığını kontrol et
        String expectedTitle = "Facebook";
        String actualTitle = "Google";
        softAssert.assertEquals(actualTitle, expectedTitle, "Başlıklar eşit değil!");

        // 2. Koşulun doğru olup olmadığını kontrol et
        softAssert.assertTrue(actualTitle.contains("Google"), "Başlık Google içermiyor!");

        // 3. Sayıların eşit olup olmadığını kontrol et
        int expectedNumber = 10;
        int actualNumber = 5;
        softAssert.assertEquals(actualNumber, expectedNumber, "Sayılar eşit değil!");

        // 4. Boolean değerini kontrol et
        boolean isUserLoggedIn = false;
        softAssert.assertTrue(isUserLoggedIn, "Kullanıcı giriş yapmamış!");

        System.out.println("Bu satır, assert başarısız olsa bile çalışır.");

        // Son olarak assertAll() çağrısı ile tüm assertion sonuçlarını kontrol et
        softAssert.assertAll();  // Eğer bir hata varsa burada raporlanır.
    }

    @Test
    public void softAssertWithoutAssertAll() {
        System.out.println("Soft Assert Without assertAll() Test başlıyor...");

        SoftAssert softAssert = new SoftAssert();

        // 1. Başlıkların eşit olup olmadığını kontrol et
        String expectedTitle = "Facebook";
        String actualTitle = "Google";
        softAssert.assertEquals(actualTitle, expectedTitle, "Başlıklar eşit değil!");

        // 2. Sayıların eşit olup olmadığını kontrol et
        int expectedNumber = 10;
        int actualNumber = 5;
        softAssert.assertEquals(actualNumber, expectedNumber, "Sayılar eşit değil!");

        // AssertAll çağrısı yapılmadığı için buradaki hatalar raporlanmaz
        System.out.println("assertAll() çağrılmadığı için hatalar raporlanmayacak.");

        // SoftAssert hatalarını kontrol etmek için assertAll()'u çağırmalıyız
        // Ancak burada çağırmadık!
    }

    @Test
    public void combinedHardAndSoftAssertTest() {
        System.out.println("Hard ve Soft Assert Test başlıyor...");

        SoftAssert softAssert = new SoftAssert();

        // İlk olarak Hard Assert kullanıyoruz
        String expectedUrl = "https://www.example.com";
        String actualUrl = "https://www.example.com";
        Assert.assertEquals(actualUrl, expectedUrl, "URL'ler eşit değil!");  // Eğer bu başarısız olursa test durur

        // Şimdi Soft Assert ile devam ediyoruz
        String expectedTitle = "Example Title";
        String actualTitle = "Sample Title";
        softAssert.assertEquals(actualTitle, expectedTitle, "Başlıklar eşit değil!");

        int expectedCount = 100;
        int actualCount = 85;
        softAssert.assertEquals(actualCount, expectedCount, "Sayacın değeri eşit değil!");

        System.out.println("Soft Assert sonuçları assertAll() çağrıldığında kontrol edilecek.");

        // SoftAssert sonuçlarını kontrol etmek için assertAll() çağrısı yapılmalı
        softAssert.assertAll();  // Soft Assert sonuçları burada kontrol edilecek
    }
}
