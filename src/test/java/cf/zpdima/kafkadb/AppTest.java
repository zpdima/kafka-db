package cf.zpdima.kafkadb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void testOnDev()
    {
        System.setProperty("ENV", "DEV");
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")), AppTest::message);
    }

    @Test
    void testOnProd()
    {
        System.setProperty("ENV", "PROD");
        Assumptions.assumeFalse("DEV".equals(System.getProperty("ENV")));
    }

    @Test
    void testSame(){
        String a1 = "aaa";
        String a2 = "aaa";
        String a3 = "dbb";

        Assumptions.assumeTrue(a1 != a3);
        Assertions.assertNotSame(a1, a3);
        Assumptions.assumeTrue(a1 == a2);
        Assertions.assertSame(a1, a2);
        Assumptions.assumeTrue(a1 == a3);
        Assertions.assertSame(a1, a3);
    }

    private static String message () {
        return "TEST Execution Failed :: ";
    }
}
