package io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ObjectDino;

public interface AutobindInterface {

    //this interface gets implemented by the the pojo class
    //then when the factory pattern class that dispatches object creation from the Stringbuilder(initially) it accepts this interfaces object as a template as  we wont be knowing what programmer will be naming its pojo class(naturally,ugh)
    //and it will have a method where the getint or getdouble maybe set? that is auto set to constructor like in the in the primitive fields? as in :
    /*
     * @Override
     * public void getters(){
     * request.getint("thedatathatthatisintinnature")...
     * }
     */
    // Core binding method - gets called with the parsed tree
    void bind(Dino jsonTree);

    // Optional: Type-safe getters with automatic conversion
    default int getInt(String key, Dino tree) {
        Dino node = findValue(key, tree);
        return node != null ? Integer.parseInt(node.value) : 0;
    }

    default double getDouble(String key, Dino tree) {
        Dino node = findValue(key, tree);
        return node != null ? Double.parseDouble(node.value) : 0.0;
    }

    default String getString(String key, Dino tree) {
        Dino node = findValue(key, tree);
        return node != null ? node.value : null;
    }

    // Helper method to find values by key
    private Dino findValue(String key, Dino tree) {
        if (tree instanceof ObjectDino) {
            return ((ObjectDino) tree).findChildByKey(key);
        }
        return null;
    }

}
/*

*/