package br.com.iteris.universidade.testes.intro.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class EmailUtils {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isValid(final String emailAddress){
        final var regexPattern = Pattern.compile(EMAIL_PATTERN);
        final var regMatcher = regexPattern.matcher(emailAddress);
        return regMatcher.matches();
    }
}
