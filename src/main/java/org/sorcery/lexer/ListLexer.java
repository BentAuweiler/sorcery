package org.sorcery.lexer;

public class ListLexer extends Lexer {

    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LEFTBRACKET = 4;
    public static int RIGTHBRACKET = 5;
    public static int INTEGER = 6;
    public static int FLOAT = 7;
    public static String[] tokenNames = {
        "n/a",
        "<EOF>",
        "NAME",
        "COMMA",
        "LEFTBRACKET",
        "RIGTHBRACKET",
        "INTEGER",
        "FLOAT",
    };

    public String getTokenName(int x){
        return tokenNames[x];
    }

    public ListLexer(String input){
        super(input);
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
                    } else if(isNumber()){
                        return getNumberToken();
                    }else{
                        throw new Error("invalid charackter: " + this.current_charackter);
                    }

            }
        }
        return new Token(EOF, "<EOF>");
    }

    public boolean isLetter(){
        return this.current_charackter >= 'a' && this.current_charackter <= 'z' || this.current_charackter >= 'A' && this.current_charackter <= 'Z';
    }

    public boolean isNumber(){
        return this.current_charackter >= '0' && this.current_charackter <= '9';
    }

    public boolean isDot(){
        return this.current_charackter == '.';
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

    private Token getNumberToken(){
        StringBuilder builder = new StringBuilder();
        boolean foundDot = false;
        int numberOfDots = 0;
        do {
            if(this.current_charackter == '.'){
                foundDot = true;
                numberOfDots++;
            }
            if(numberOfDots > 1){
                throw new Error("invalid charackter: " + this.current_charackter);
            }
            builder.append(this.current_charackter);
            consume();
        } while(isNumber() || isDot());
        if(foundDot){
            return new Token(FLOAT, builder.toString());
        }else{
            return new Token(INTEGER, builder.toString());
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