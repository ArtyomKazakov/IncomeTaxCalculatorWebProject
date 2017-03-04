package by.iba.calculator.command.util;

import org.apache.log4j.Logger;

/**
 * Provides some useful methods for commands.
 */
public class CommandHelper {
    private final static Logger logger = Logger.getLogger(CommandHelper.class);

    /**
     * Gets int value from string
     *
     * @param innerString a string with int value
     * @return number
     */
    public static int getInt(String innerString) {
        if (innerString != null) {
            try {
                int value = Integer.parseInt(innerString);
                return value;
            } catch (NumberFormatException e) {
                logger.error("Wrong string for getting number");
                return -1;
            }
        } else {
            return -1;
        }
    }
}
