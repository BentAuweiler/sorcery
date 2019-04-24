package org.sorcery.parser;

import org.sorcery.lexer.*;

public class ListParser extends Parser {

    public ListParser(Lexer lexer){
        super(lexer);
    }

    public void elementOfList(){
        if(this.lookahead.type == ListLexer.NAME){
            match(ListLexer.NAME);
        }else if(this.lookahead.type == ListLexer.LEFTBRACKET){
            foundEndOfList();
        }else{
            throw new Error("expecting name or list; found " + this.lookahead);
        }
    }

    public void foundEndOfList(){
        match(ListLexer.LEFTBRACKET);
        parseElementsOfList();
        match(ListLexer.RIGTHBRACKET);
    }

    public void parseElementsOfList(){
        elementOfList();
        while(this.lookahead.type == ListLexer.COMMA){
            match(ListLexer.COMMA);
            elementOfList();
        }
    }
}