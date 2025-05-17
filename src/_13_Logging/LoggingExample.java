package _13_Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class LoggingExample extends BaseDriver {

    private static final Logger logger = LogManager.getLogger(LoggingExample.class);
    @Test
    public void logTest() {
        // Bilgilendirme mesajı
        logger.info("Test başladı: logTest()");

        try {
            // Test işlemi (örneğin, iki sayıyı toplama)
            int result = 5 + 3;
            logger.debug("Toplama işlemi yapılıyor: 5 + 3 = " + result);

            // Başarılı durumda bilgilendirme
            logger.info("Test başarılı: Sonuç = " + result);
        } catch (Exception e) {
            // Hata durumu
            logger.error("Test başarısız oldu", e);
        }

        logger.info("Test bitti: logTest()");
    }
}
