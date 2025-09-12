package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.*;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.FactoryInitiation;

import java.util.List;
import java.util.Stack;

public class SimpleParser {
    public Dino parse(List<Token> tokens) {
        Stack<Dino> stack = new Stack<>();
        Dino root = null;
        String pendingKey = null;

        for (Token token : tokens) {
            switch (token.type) {
                case OPEN_BRACE:
                case OPEN_BRACKET:
                    Token newToken = new Token(token.type, pendingKey, null);
                    Dino node = FactoryInitiation.toinit(newToken);

                    if (!stack.isEmpty()) {
                        stack.peek().addchild(node);
                    }
                    if (root == null) root = node;

                    stack.push(node);
                    pendingKey = null;
                    break;

                case STRING:
                    if (pendingKey == null) {
                        pendingKey = token.value;  // This is a key
                    } else {
                        // This is a value
                        Token valueToken = new Token(TokenType.STRING, pendingKey, token.value);
                        Dino value = FactoryInitiation.toinit(valueToken);
                        stack.peek().addchild(value);
                        pendingKey = null;
                    }
                    break;

                case CLOSE_BRACE:
                case CLOSE_BRACKET:
                    stack.pop();
                    break;
            }
        }

        return root;
    }
}
