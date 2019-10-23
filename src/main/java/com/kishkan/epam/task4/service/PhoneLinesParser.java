package com.kishkan.epam.task4.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneLinesParser {

    public String parsePhoneNumbers(String input) {
        Pattern pattern = Pattern.compile("\\+\\d\\(\\d{3}\\)\\s\\d{3}(\\s\\d{2}){2}");
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String match = input.substring(matcher.start(), matcher.end());
            result.append(trimMatch(match)).append("\n");
        }
        return result.toString();
    }

    private String trimMatch(String match) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(match);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String trimmedMatch = match.substring(matcher.start(), matcher.end());
            result.append(trimmedMatch);
        }
        return result.toString();
    }
}
