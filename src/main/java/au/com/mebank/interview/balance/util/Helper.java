package au.com.mebank.interview.balance.util;

import au.com.mebank.interview.balance.Config;
import au.com.mebank.interview.balance.Constant;
import au.com.mebank.interview.balance.model.Transaction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Helper class
 * @author ltiancong@gmail.com
 * @date 2019/7/16 7:30 PM
 */
public class Helper {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(Constant
            .DATE_FORMAT);

    private static Config config = Config.getInstance();

    private final static Logger LOGGER = Logger.getLogger(Helper.class.getName());

    private static class CustomMatchingStrategy extends
            HeaderColumnNameMappingStrategy {
        @Override
        public void verifyLineLength(int numberOfFields) throws CsvRequiredFieldEmptyException {
            if (!this.headerIndex.isEmpty() && numberOfFields > this.headerIndex
                    .getHeaderIndexLength()) {
                System.out.println(numberOfFields);
                System.out.println(this.headerIndex.getHeaderIndexLength());
                throw new CsvRequiredFieldEmptyException(this.type,
                        ResourceBundle.getBundle("opencsv", this.errorLocale).getString("header.data.mismatch"));
            }
        }
    }

    /**
     * parse the transaction csv into a list
     * @return
     */
    public static List<Transaction> parseTransactionHistory() throws
            IOException {
        try(Reader reader = new InputStreamReader(
                ClassLoader.getSystemResourceAsStream(config.getInputFile()))) {
            CustomMatchingStrategy ms = new CustomMatchingStrategy();
            ms.setType(Transaction.class);

            CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Transaction.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withMappingStrategy(ms)
                    .withIgnoreQuotations(true)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            LOGGER.severe("Error: wrong config file, " + config.getInputFile());
            throw e;
        }
    }

    /**
     * get Date object from input
     * @param scanner
     * @param dateField
     * @return
     */
    public static Date getDate(Scanner scanner, String dateField) {
        String rawDate = getInput(scanner, dateField);
        try {
            return DATE_FORMAT.parse(rawDate);

        } catch (ParseException e) {
            System.out.printf("> Error: Wrong %s date format. " +
                    "The correct format is [dd/MM/yyyy HH:mm:ss]. " +
                    "please input again.\n", dateField);
            return getDate(scanner, dateField);
        }

    }

    public static String getInput(Scanner scanner, String inputField) {
        System.out.printf("> %s: ", inputField);
        String inputValue = scanner.nextLine();
        return inputValue.trim();
    }
}
