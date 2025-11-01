package _12_HTMLReporting;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleHTMLReportTest {

    @Test
    public void testLoginSuccess() {
        System.out.println("Giriş testi çalışıyor...");

        // Başarılı giriş için test senaryosu
        String expectedTitle = "Hesabım";
        String actualTitle = "Hesabım";  // Giriş sonrası simüle edilen başlık
        Assert.assertEquals(actualTitle, expectedTitle, "Giriş sonrası başlık eşleşmiyor!");
    }

    @Test
    public void testLoginFailure() {
        System.out.println("Başarısız giriş testi çalışıyor...");

        // Hatalı giriş için test senaryosu
        String expectedErrorMessage = "Geçersiz kimlik bilgileri";
        String actualErrorMessage = "Geçersiz kimlik bilgileri";  // Başarısız giriş sonrası simüle edilen hata mesajı
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Başarısız girişte hata mesajı yanlış!");
    }

    @Test
    public void testLogout() {
        System.out.println("Çıkış testi çalışıyor...");

        // Başarılı çıkış için test senaryosu
        boolean isLoggedOut = true;  // Simüle edilen çıkış durumu
        Assert.assertTrue(isLoggedOut, "Kullanıcı çıkış yapamadı!");
    }
}
