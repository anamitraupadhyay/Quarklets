package io.github.anamitraupadhyay.Quarklets.experimetal.datastructure;

public class ObjectDino extends Dino /*implements autoparse likerunnableandprocess*/ {


	public ObjectDino(/*Dino[] childdinoarray, String value,*/ String key) {
		super(Jsontype.OBJECT, /*childdinoarray,*/ null, key);
		//TODO Auto-generated constructor stub
	}

    public Dino findChildByKey(String key) {//audit changes is needed suppose key is passed here and from the object i need the corresponding value wait its about find child using key so iguess i return the dino that is the required one ok guess the accessing part is gonna be tough
        if (key == null || childdinoarray == null) {
            return null;
        }
        
        // Iterate through children to find matching key
        for (int i = 0; i < childcount; i++) {
            Dino child = childdinoarray[i];
            if (child != null && key.equals(child.key)) {
                return child;
            }
        }
        return null;
    }
    // specialized behavior for OBJECT nodes
	/*public void parsing() {
		ObjectDino obj = new ObjectDino();
	}*/
}
