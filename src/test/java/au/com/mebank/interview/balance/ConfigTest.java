package au.com.mebank.interview.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author ltiancong@gmail.com
 * @date 2019/7/18 12:08 PM
 */
public class ConfigTest {
    private Config config = Config.getInstance();

    @Test
    void testGetInputFile() {
        assertEquals(config.getInputFile(), "transactions.csv");
    }
}