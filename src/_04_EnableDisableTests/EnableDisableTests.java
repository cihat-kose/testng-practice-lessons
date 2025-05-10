package _04_EnableDisableTests;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class EnableDisableTests {

    // Bu metot `enabled = false` olduğu için çalıştırılmayacak.
    @Test(enabled = false)
    public void testCaseDisabled() {
        String str1 = "TestNG";
        String str2 = "Selenium";
        Assert.assertEquals(str1, str2, "String'ler eşit değil! TestNG ve Selenium farklıdır.");
        System.out.println("Bu metot çalıştırılmadı çünkü `enabled = false` ile devre dışı bırakıldı.");
    }

    // Bu metot çalıştırılacak çünkü varsayılan olarak `enabled = true`.
    @Test
    public void testCaseEnabled() {
        String str1 = "Selenium";
        String str2 = "Selenium";
        Assert.assertEquals(str1, str2, "String'ler eşit olmalı! İkisi de Selenium'dur.");
        System.out.println("Enabled test metodu çalıştı.");
    }

    // Bu metot da çalışacak.
    @Test(priority = 1)
    public void testCaseOne() {
        String str1 = "Java";
        String str2 = "Java";
        Assert.assertEquals(str1, str2, "String'ler eşit olmalı! İkisi de Java'dır.");
        System.out.println("Test case one başarıyla çalıştı.");
    }

    // Bu metot `enabled = false` ile devre dışı bırakıldı.
    @Test(priority = 2, enabled = false)
    public void testCaseTwo() {
        String str1 = "TestNG";
        String str2 = "JUnit";
        Assert.assertEquals(str1, str2, "String'ler eşit değil! TestNG ve JUnit farklıdır.");
        System.out.println("Bu metot çalıştırılmadı çünkü `enabled = false` ile devre dışı bırakıldı.");
    }

    // Normal çalışan bir test metodu.
    @Test(priority = 3)
    public void testCaseThree() {
        String str1 = "Selenium WebDriver";
        String str2 = "Selenium WebDriver";
        Assert.assertEquals(str1, str2, "String'ler eşit olmalı! İkisi de Selenium WebDriver'dır.");
        System.out.println("Test case three başarıyla çalıştı.");
    }

    // @Ignore kullanılarak devre dışı bırakılan bir test metodu.
    @Ignore
    @Test
    public void testCaseIgnored() {
        String str1 = "TestNG";
        String str2 = "Selenium";
        Assert.assertEquals(str1, str2, "String'ler eşit değil! TestNG ve Selenium farklıdır.");
        System.out.println("Bu metot çalıştırılmadı çünkü `@Ignore` annotation'ı ile devre dışı bırakıldı.");
    }
}
