package _03_Annotations;

import org.testng.annotations.*;

public class TestNGAnnotationsExample {

    /**
     * @BeforeSuite
        * @BeforeTest
             * @BeforeGroups
                * @BeforeClass
                  * @BeforeMethod
                            * @Test
                            * @Test
                 * @AfterMethod
                 * @AfterClass
             * @AfterGroups
         * @AfterTest
     * @AfterSuit
     */

    // Test suite çalışmadan önce çalışır
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite çalıştı.");
    }

    // Test grubundan önce çalışır
    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test çalıştı.");
    }

    // Sınıftan önce çalışır
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class çalıştı.");
    }

    // Her test metodundan önce çalışır
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method çalıştı.");
    }

    // İlk test methodu
    @Test
    public void testMethodOne() {
        System.out.println("Test Method One çalıştı.");
    }

    // İkinci test methodu
    @Test
    public void testMethodTwo() {
        System.out.println("Test Method Two çalıştı.");
    }

    // Her test metodundan sonra çalışır
    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method çalıştı.");
    }

    // Sınıftan sonra çalışır
    @AfterClass
    public void afterClass() {
        System.out.println("After Class çalıştı.");
    }

    // Test grubundan sonra çalışır
    @AfterTest
    public void afterTest() {
        System.out.println("After Test çalıştı.");
    }

    // Test suite tamamlandıktan sonra çalışır
    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite çalıştı.");
    }
}
