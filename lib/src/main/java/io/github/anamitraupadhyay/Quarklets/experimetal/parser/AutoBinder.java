package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;

public abstract class AutoBinder {
    public Dino tree;

    public setroot(Dino root){ this.tree = root; bind();}
    public abstract void bind();

    public int getInt(String key /*, Dino tree*/) {
        Dino node = findValue(key, this.tree);
        return node != null ? Integer.parseInt(node.value) : 0;
    }

    public double getDouble(String key/*, Dino tree*/) {
        Dino node = findValue(key, this.tree);
        return node != null ? Double.parseDouble(node.value) : 0.0;
    }

    public String getString(String key/*, Dino tree*/) {
        Dino node = findValue(key, this.tree);
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
