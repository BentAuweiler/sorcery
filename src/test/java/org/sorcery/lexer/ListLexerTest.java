package org.sorcery.lexer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.sorcery.utility.*;

/**
 * Unit test for simple App.
 */
public class ListLexerTest {

    private String filePath = "fixtures/lexer/sample-1.sorc";
    
    @Test
    public void shouldTokenizeFirstSampleFile() throws IOException {
        String fileContent = FileHelper.getFileContentAsString(filePath);
        ListLexer lexer = new ListLexer(fileContent);
        
        String expectedFirstToken = "<'[',LEFTBRACKET>";
        Token actualFirstToken = lexer.nextToken();
        assertEquals(expectedFirstToken, actualFirstToken.toString());

        String expectedSecondToken = "<'1',INTEGER>";
        Token actualSecondToken = lexer.nextToken();
        assertEquals(expectedSecondToken, actualSecondToken.toString());

        String expectedThirdToken = "<',',COMMA>";
        Token actualThirdToken = lexer.nextToken();
        assertEquals(expectedThirdToken, actualThirdToken.toString());

        String expectedFourthToken = "<'2.2',FLOAT>";
        Token actualFourthToken = lexer.nextToken();
        assertEquals(expectedFourthToken, actualFourthToken.toString());
    }

}