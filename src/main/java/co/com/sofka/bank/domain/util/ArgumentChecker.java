package co.com.sofka.bank.domain.util;

import org.apache.logging.log4j.util.Strings;

import java.util.function.Predicate;

public class ArgumentChecker {
    private ArgumentChecker() {}

    public static void checkIsValidString(String input, String msgIfInvalidInput) {
        if(Strings.isBlank(input))
            throw new IllegalArgumentException(msgIfInvalidInput);
    }

    public static <T> void checkIsValidArgument(Predicate<T> predicate, T input, String msgIfInvalidInput) {
        if(!predicate.test(input))
            throw new IllegalArgumentException(msgIfInvalidInput);
    }
}
