package io.github.anamitraupadhyay.Quarklets.experimetal.datastructure;

public class ArrayDino extends Dino {

	ArrayDino(/*Dino[] childdinoarray, String value,*/ String key) {
		super(Jsontype.ARRAY, /*childdinoarray,*/ null, key);
		//TODO Auto-generated constructor stub
	}

    public Dino findChildByKey(String key) {
        return null;
    }
    // specialized behavior for ARRAY nodes
}
