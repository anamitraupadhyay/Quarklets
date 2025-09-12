package io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ValueDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ObjectDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ArrayDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.Token;

import static io.github.anamitraupadhyay.Quarklets.experimetal.parser.TokenType.*;

//factory pattern
public class FactoryInitiation {

    // Holds the last-created Dino node
    public static Dino result;

    public static Dino toinit(Token token) {
        switch (token.type) {
            case OPEN_BRACE:
                return new ObjectDino(token.key);
            case OPEN_BRACKET:
                return new ArrayDino(token.key);
            case STRING:
                return new ValueDino(token.key, token.value);
            default:
                throw new IllegalArgumentException("Cannot create Dino from: " + token.type);
        }
    }
}
