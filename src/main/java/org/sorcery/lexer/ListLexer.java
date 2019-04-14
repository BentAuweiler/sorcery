package org.sorcery.lexer;

public class ListLexer extends Lexer {

    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LEFTBRACKET = 4;
    public static int RIGTHBRACKET = 5;
    public static String[] tokenNames = {
        "n/a",
        "<EOF>",
        "NAME",
        "COMMA",
        "LEFTBRACKET",
        "RIGTHBRACKET",
    };

    public String getTokenName(int x){
        return tokenNames[x];
    }

    public ListLexer(String input){
        super(input);
    }

    public boolean isLetter(){
        return this.current_charackter >= 'a' && this.current_charackter <= 'z' || this.current_charackter >= 'A' && this.current_charackter <= 'Z';
    }

    public Token nextToken(){
        while(this.current_charackter != EOF){
            switch (this.current_charackter){
                case ' ': case '\t': case '\n': case '\r': consumeWhiteSpaces(); continue;
                case ',': consume(); return new Token(COMMA, ",");
                case '[': consume(); return new Token(LEFTBRACKET, "[");
                case ']': consume(); return new Token(RIGTHBRACKET, "]");
                default:
                    if(isLetter()){
                        return getVariableToken();
                    } else{
                        throw new Error("invalid charackter: " + this.current_charackter);
                    }

            }
        }
        return new Token(EOF, "<EOF>");
    }

    private void consumeWhiteSpaces(){
        while(isWhiteSpace()){
            consume();
        }
    }

    private boolean isWhiteSpace(){
        if(this.current_charackter == ' ' || this.current_charackter == '\t' || this.current_charackter == '\n' || this.current_charackter == '\r'){
            return true;
        } else {
            return false;
        }
    }

    private Token getVariableToken(){
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(this.current_charackter);
            consume();
        } while(isLetter());
        return new Token(NAME, builder.toString());
    }
}