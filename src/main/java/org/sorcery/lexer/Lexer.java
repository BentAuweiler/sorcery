package org.sorcery.lexer;

public abstract class Lexer{

    public static final char EOF = (char) -1;
    public static final int EOF_TYPE = 1;
    String input;
    int pointer = 0;
    char current_charackter;

    public Lexer(String input){
        this.input = input;
        current_charackter = input.charAt(pointer);
    }

    public void consume(){
        pointer++;
        if(pointer >= this.input.length()){
            current_charackter = EOF;
        } else{
            current_charackter = input.charAt(pointer);
        }
    }

    public void match(char x){
        if(current_charackter == x) consume();
        else throw new Error("expecting charackter " + x + "; found " + current_charackter);
    }

    public abstract Token nextToken();
    public abstract String getTokenName(int tokenType);
}