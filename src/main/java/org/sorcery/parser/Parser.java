package org.sorcery.parser;

import org.sorcery.lexer.*;

public abstract class Parser{

    private Lexer lexer;
    protected Token lookahead;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public void match(int x){
        if(this.lookahead.type == x){
            consume();
        }
    }

    public void consume(){
        this.lookahead = lexer.nextToken();
    }
}