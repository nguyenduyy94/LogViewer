package com.logviewer.LogViewer.utils.SearchQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Token {
    private String text;
    private boolean isRegex =false;

    public static final String OR_DELIMITER = "\\|\\|";
    public static final String AND_DELIMITER = "&&";
    public static final String START_AND_STATEMENT = "(";
    public static final String END_AND_STATEMENT = ")";
    public static final String REGEX = "~";

    public static Token from(String s) {
        Token token = new Token();
        token.setText(s.trim());
        if (token.getText().startsWith(Token.REGEX)) {
            token.setRegex(true);
            token.setText(s.substring(1));
        }
        return token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRegex() {
        return isRegex;
    }

    public void setRegex(boolean regex) {
        isRegex = regex;
    }

    public boolean match(String source) {
        if (isRegex) {
            Pattern pattern = Pattern.compile(text);
            Matcher matcher = pattern.matcher(source);
            return matcher.matches();
        } else {
            return source.contains(text);
        }
    }
}
