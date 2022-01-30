package stringcalculator.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import stringcalculator.domain.Delimiters;

public class InputView {

    private static final int PREFIX_LENGTH = 2;
    private static final String DEFAULT_DELIMITER_COMMA = ",";
    private static final String DEFAULT_DELIMITER_COLON = ":";
    private static final String EMPTY_STRING = "";
    private static final String PREFIX_REGEX = "\\/\\/(.+)";
    private static final String DEFAULT_NUMBER = "0";

    public static Delimiters getDelimiters() {
        List<String> delimiters = Arrays.asList(DEFAULT_DELIMITER_COMMA, DEFAULT_DELIMITER_COLON);
        String input = getInput();

        if (!isBlank(input)) {
            validatePrefix(input);
            delimiters = Arrays.asList(getRemovePrefix(input));
        }

        return new Delimiters(delimiters);
    }

    public static String getExpression() {
        String input = getInput();

        if (isBlank(input)) {
            return DEFAULT_NUMBER;
        }
        return input;
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    private static String getInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    private static boolean isBlank(String input) {
        input = input.trim();
        return input.isEmpty() || input.equals(EMPTY_STRING);
    }

    private static void validatePrefix(String input) {
        if (!input.matches(PREFIX_REGEX)) {
            throw new IllegalArgumentException("올바른 입력형식이 아닙니다. ex) //${delimiter}");
        }
    }

    private static String getRemovePrefix(String input) {
        return input.substring(PREFIX_LENGTH);
    }
}
