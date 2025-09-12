package io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic;

import io.github.anamitraupadhyay.Quarklets.experimetal.parser.Token;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> JsonParse(StringBuilder json, int startPos) {
        List<Token> tokens = new ArrayList<>();
        int i = startPos;

        while (i < json.length()) {
            char c = json.charAt(i);

            switch (c) {
                case '{': tokens.add(new Token(TokenType.OPEN_BRACE)); break;
                case '}': tokens.add(new Token(TokenType.CLOSE_BRACE)); break;
                case '[': tokens.add(new Token(TokenType.OPEN_BRACKET)); break;
                case ']': tokens.add(new Token(TokenType.CLOSE_BRACKET)); break;
                case ':': tokens.add(new Token(TokenType.COLON)); break;
                case ',': tokens.add(new Token(TokenType.COMMA)); break;
                case '"':
                    // Simple string parsing
                    int end = json.toString().indexOf('"', i + 1);
                    String str = json.substring(i + 1, end);
                    tokens.add(new Token(TokenType.STRING, null, str));
                    i = end;
                    break;
                case ' ': case '\t': case '\n': case '\r':
                    // Skip whitespace
                    break;
                default:
                    // Handle numbers and other values
                    if (Character.isDigit(c) || c == '-' || c == '.') {
                        int start = i;
                        while (i < json.length() && 
                               (Character.isDigit(json.charAt(i)) || 
                                json.charAt(i) == '.' || 
                                json.charAt(i) == '-' ||
                                json.charAt(i) == 'e' ||
                                json.charAt(i) == 'E' ||
                                json.charAt(i) == '+')) {
                            i++;
                        }
                        String number = json.substring(start, i);
                        tokens.add(new Token(TokenType.STRING, null, number));
                        i--; // Adjust because the loop will increment
                    }
                    break;
            }
            i++;
        }
        return tokens;
    }
}
