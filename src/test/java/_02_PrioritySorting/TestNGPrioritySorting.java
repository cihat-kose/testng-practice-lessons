package _02_PrioritySorting;

import org.testng.annotations.Test;

public class TestNGPrioritySorting {
    /**
        Bu metot priority = 1 ile tanımlandı.
        Priority değeri 1 olduğu için, bu metot priority atanmış diğer metotlar arasında
        en önce çalıştırılacak.
     */
    @Test(priority = 1)
    public void testMethodOne() {
        System.out.println("TestNG Method One - Priority 1");
    }

    /**
        Bu metot priority = 2 ile tanımlandı.
        Priority sırası ile bu metot, priority 1 olan metottan sonra çalıştırılacaktır.
     */
    @Test(priority = 2)
    public void testMethodTwo() {
        System.out.println("TestNG Method Two - Priority 2");
    }

    /**
        Bu metot için de herhangi bir priority atanmadı.
        TestNG bu metodu alfabetik sırasına göre çalıştıracaktır.
        `testMethodFive`'den sonra, alfabetik sırada bu metot gelecektir.
     */
    @Test
    public void testMethodThree() {
        System.out.println("TestNG Method Three - Priority Yok");
    }

    /**
        Bu metot priority = 3 ile tanımlandı.
        Priority değeri 3 olduğu için, bu metot diğer priority değerleri olan metotlardan
        sonra çalıştırılacaktır.
     */
    @Test(priority = 3)
    public void testMethodFour() {
        System.out.println("TestNG Method Four - Priority 3");
    }

    /**
        Bu metot için herhangi bir priority atanmadı.
        Bu yüzden TestNG bu metodu alfabetik sıraya göre çalıştıracaktır.
     */
    @Test()
    public void testMethodFive() {
        System.out.println("TestNG Method Five - Priority Yok");
    }
}
