package io.github.anamitraupadhyay.Quarklets.experimetal.datastructure;

public class ValueDino extends Dino {

	public ValueDino(/*Dino[] childdinoarray,*/ String value, String key) {
		super(Jsontype.VALUES/*,childdinoarray*/, value, key);
		//TODO Auto-generated constructor stub
	}

    @Override
    public Dino findChildByKey(String key) {
        return null;
    }
    // specialized behavior for VALUE nodes
}
