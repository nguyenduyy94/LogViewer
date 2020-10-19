package com.logviewer.LogViewer.utils.SearchQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchQuery {
    private List<ORStatement> orStatements = new ArrayList<>();


    public static SearchQuery parse(String q) {
        SearchQuery searchQuery = new SearchQuery();
        String[] statements = q.split(Token.OR_DELIMITER);
        for (String s : statements) {
            if (s != null && !s.isEmpty()) {
                searchQuery.getOrStatements().add(ORStatement.from(s));
            }
        }
        return searchQuery;
    }

    public List<ORStatement> getOrStatements() {
        return orStatements;
    }

    public void setOrStatements(List<ORStatement> orStatements) {
        this.orStatements = orStatements;
    }

    public boolean matchLine(String line) {
        for (ORStatement orStatement : orStatements) {
            List<Token> tokens = orStatement.getTokens();
            if (tokens == null || tokens.size() == 0) continue;
            boolean value = true;
            for (Token token : tokens) {
                value &= token.match(line);
            }
            if (value) return true;
        }

        return false;
    }


}
