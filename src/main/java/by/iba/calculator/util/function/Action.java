package by.iba.calculator.util.function;

/**
 * Represents an operation that has no argument and returns no result.
 */
@FunctionalInterface
public interface Action {
    /**
     * Performs this operation.
     *
     * @throws Exception if occurred some error
     */
    void execute() throws Exception;
}
