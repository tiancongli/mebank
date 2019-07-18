package au.com.mebank.interview.balance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Load config file from resource folder, config.properties
 * @author ltiancong@gmail.com
 * @date 2019-07-18 14:15
 */
public class Config {
    private static Config instance = new Config();

    private final static Logger LOGGER =
            Logger.getLogger(Config.class.getName());

    private Properties properties = new Properties();

    public static Config getInstance() {
        return instance;
    }

    private Config() {

        try(InputStream in =
                    ClassLoader.getSystemResourceAsStream(Constant.CONFIG_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            LOGGER.severe("Error: config file not exist, " + Constant.CONFIG_FILE);
        }
    }

    public String getInputFile() {
        String inputFile = properties.getProperty(Constant.INPUT_FILE);
        if (inputFile == null) {
            LOGGER.severe("Error: input file property not exist");
            throw new IllegalStateException();
        }
        return inputFile;
    }
}
