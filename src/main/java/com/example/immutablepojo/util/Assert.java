package com.example.immutablepojo.util;


import java.util.regex.Pattern;

public final class Assert {

    private Assert() {
    }

    public static void hasText(String text, String message) {
        if (!hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static void hasPattern(String pattern, String value, String message) {
        if (!hasText(value) || !hasPattern(pattern, value)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean hasPattern(String pattern, String value) {
        return Pattern.matches(pattern, value);
    }
}
