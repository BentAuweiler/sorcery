package org.sorcery.parser;

import java.util.List;

import org.sorcery.lexer.*;

public abstract class Parser{

    private Lexer lexer;
    private List<Integer> markers;
    protected List<Token> lookahead;
    private int pointer;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public void match(int x) throws MismatchedTokenException {
        if( getTokenTypeFromLookahead(1) == x ){
            consume();
        }else{
            throw new MismatchedTokenException("expecting " + lexer.getTokenName(x) + " found " + getTokenAtLookaheadPosition(1));
        }
    }

    public void consume(){
        this.pointer++;
        if( this.pointer == this.lookahead.size() && !isSpeculating() ){
            this.pointer = 0;
            this.lookahead.clear();
        }
        sync(1);
    }

    public Token getTokenAtLookaheadPosition(int position){
        sync(position);
        int currentPosition = this.pointer+position-1;
        return this.lookahead.get( currentPosition );
    }

    public int getTokenTypeFromLookahead(int i){
        return getTokenAtLookaheadPosition(i).type;
    }

    public void sync(int i){
        int currentPosition = this.pointer+i-1;
        int numberOfTokens = this.lookahead.size()-1;
        if(currentPosition > numberOfTokens){
            int n = currentPosition - numberOfTokens;
            fill(n);
        }
    }

    public void fill(int number){
        for(int i = 1; i <= number; i++ ){
            lookahead.add(lexer.nextToken());
        }
    }

    public boolean isSpeculating(){
        return this.markers.size() > 0;
    }

    public int mark(){
        this.markers.add(this.pointer);
        return this.pointer;
    }

    public void release(){
        int marker = this.markers.size() - 1 ;
        this.markers.remove(this.markers.size() - 1);
        setPointer(marker);
    }

    public void setPointer(int index){
        this.pointer = index;
    }
}