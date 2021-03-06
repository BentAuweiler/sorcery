package org.sorcery.lexer;

public class Token{

    public int type;
    public String text;

    public Token(int type, String text){
        this.type = type;
        this.text = text;
    }

    public String toString(){
        String name = ListLexer.tokenNames[this.type];
        return "<'" + this.text + "'," + name + ">";
    }
}