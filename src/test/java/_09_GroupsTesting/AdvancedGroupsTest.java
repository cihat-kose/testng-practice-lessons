package _09_GroupsTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvancedGroupsTest {

    /**
     * Sanity Test Grubu
     * Bu test, sistemin temel düzeyde çalışıp çalışmadığını kontrol eder.
     * Genellikle testlerin çalıştırılmasından önce sistemin ayakta olduğunun doğrulanması için kullanılır.
     */
    @Test(groups = "sanity")
    public void basicSanityCheck() {
        System.out.println("Sanity Test: Temel doğrulama yapılıyor.");
        Assert.assertTrue(true, "Sanity testi başarısız oldu.");
    }

    /**
     * Functional Test Grubu
     * Bu test, kullanıcı giriş işlevinin düzgün çalışıp çalışmadığını kontrol eder.
     * Uygulamanın login özelliğini bağımsız olarak test etmek için kullanılır.
     */
    @Test(groups = "functional")
    public void loginFunctionalityTest() {
        System.out.println("Functional Test: Giriş işlevselliği test ediliyor.");
        Assert.assertTrue(true, "Giriş işlevselliği testi başarısız oldu.");
    }

    /**
     * Functional Test Grubu
     * Bu test, bir ürünün sepete başarıyla eklenip eklenmediğini kontrol eder.
     * Kullanıcı etkileşimiyle ilgili temel bir işlevsellik testidir.
     */
    @Test(groups = "functional")
    public void addProductToCartTest() {
        System.out.println("Functional Test: Ürün sepete ekleme testi yapılıyor.");
        Assert.assertTrue(true, "Sepete ürün ekleme testi başarısız oldu.");
    }

    /**
     * Regression Test Grubu
     * Bu test, ödeme işleminin doğru çalışıp çalışmadığını kontrol eder.
     * Sanity grubuna bağımlıdır; sistem temel kontrollerden geçmeden bu test çalıştırılmaz.
     */
    @Test(groups = "regression", dependsOnGroups = "sanity")
    public void checkoutTest() {
        System.out.println("Regression Test: Ödeme işlevi test ediliyor.");
        Assert.assertTrue(true, "Ödeme işlemi testi başarısız oldu.");
    }

    /**
     * Regression Test Grubu
     * Bu test, kullanıcının önceki sipariş geçmişini görüntüleyip görüntüleyemediğini kontrol eder.
     * Sanity grubuna bağımlıdır.
     */
    @Test(groups = "regression", dependsOnGroups = "sanity")
    public void orderHistoryTest() {
        System.out.println("Regression Test: Sipariş geçmişi görüntüleme testi yapılıyor.");
        Assert.assertTrue(true, "Sipariş geçmişi testi başarısız oldu.");
    }

    /**
     * Regression Test Grubu
     * Bu test, tüm sistemin bir bütün olarak çalışıp çalışmadığını test eder.
     * Sanity ve functional gruplarının başarılı olmasına bağlı olarak çalışır.
     * Tam kapsamlı entegrasyon testi olarak değerlendirilir.
     */
    @Test(groups = "regression", dependsOnGroups = {"sanity", "functional"})
    public void fullSystemTest() {
        System.out.println("Regression Test: Tüm sistem testi.");
        Assert.assertTrue(true, "Tam sistem testi başarısız oldu.");
    }
}
