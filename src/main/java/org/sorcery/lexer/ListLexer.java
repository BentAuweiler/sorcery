package org.sorcery.lexer;

public class ListLexer extends Lexer {

    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACK = 4;
    public static int RBRACK = 5;
    public static String[] tokenNames = {
        "n/a",
        "<EOF>",
        "NAME",
        "COMMA",
        "LBRACK",
        "RBRACK",
    };

    public String getTokenName(int x){
        return tokenNames[x];
    }

    public ListLexer(String input){
        super(input);
    }

    public boolean isLetter(){
        return current_charackter >= 'a' && current_charackter <= 'z' || current_charackter >= 'A' && current_charackter <= 'Z';
    }
}