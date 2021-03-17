package com.company.Optional;

import java.util.Optional;

public class WrongOptional {
    public static void main(String[] args) {
        String text = getText();
        Optional<String> maybeText = Optional.ofNullable(text);
        int length;
        if (maybeText.isPresent()) {
            length = maybeText.get().length();
        } else {
            length = 0;
        }
    }

    public static String getText() {
        return null;
    }
}
