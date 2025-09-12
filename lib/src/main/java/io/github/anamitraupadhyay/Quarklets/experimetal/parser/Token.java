package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

public class Token {
    public TokenType type;
    public String key;
    public String value;

    public Token(TokenType type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public Token(TokenType type) {
        this(type, null, null);
    }
    
    @Override
    public String toString() {
        return String.format("Token{type=%s, key='%s', value='%s'}", type, key, value);
    }
}
