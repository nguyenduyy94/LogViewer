package com.logviewer.LogViewer.utils.SearchQuery;

import java.util.ArrayList;
import java.util.List;

public class ORStatement {
    private String text;
    private List<Token> tokens = new ArrayList<>();

    public static ORStatement from(String text) {
        ORStatement o = new ORStatement();
        o.text = text.trim();
        if (o.text.startsWith(Token.START_AND_STATEMENT) && o.text.endsWith(Token.END_AND_STATEMENT)) {
            String cleanText = o.text.substring(1, text.length() - 1);
            String[] parts = cleanText.split(Token.AND_DELIMITER);
            for (String part : parts) {
                o.tokens.add(Token.from(part.trim()));
            }
        } else {
            o.tokens.add(Token.from(o.text));
        }
        return o;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
