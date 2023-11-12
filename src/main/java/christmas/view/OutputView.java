package christmas.view;


public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s\n";

    public void printError(String message) {
        System.out.printf(ERROR_MESSAGE_FORMAT, message);
    }
}

