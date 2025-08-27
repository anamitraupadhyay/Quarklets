package io.github.anamitraupadhyay.Quarklets.experimetal.datastructure;


enum Jsontype {
	OBJECT(false, true),
	ARRAY(true, true),
	VALUES(true, false);

	private final boolean keycanbenull;
	private final boolean canhavechildren;

	Jsontype(boolean keycanbenull, boolean canhavechildren) {
		this.keycanbenull = keycanbenull;
		this.canhavechildren = canhavechildren;
	}

	public boolean getkeycanbenull() {
		return keycanbenull;
	}

	public boolean getcanhavechildren() {
		return canhavechildren;
	}
}

public abstract class Dino{
	
	
	Jsontype jsonenum;

	Dino[] childdinoarray;

	String value;

	String key;

	//char [] defaultarray; not required as the Strings will handle it

	int childcount;


	Dino(Jsontype jsonenum, Dino[] childdinoarray, String value, String key) {
		this.jsonenum = jsonenum;
		this.childdinoarray = childdinoarray;
		this.value = value;
		this.key = key;

		if (jsonenum.getcanhavechildren()) { // written in constructor as to init memory efficiently
			this.childdinoarray = new Dino[4];
		} else {
			this.childdinoarray = null;
		}
	}

	public void addchild(Dino child) //throws IllegalAccessException
	{
		while( this.childcount<this.childdinoarray.length ) {
            if (this.jsonenum.getcanhavechildren()) { //child.jsonenum is wrong logically as checking childs ability to check can have child or not
                this.childdinoarray[this.childcount] = child;
                this.childcount++;
                if(this.childcount > this.childdinoarray.length){
                    //method call to increase the size? to increasesize how to call it
                }
            } else {
                //throw new IllegalAccessException(child.jsonenum + "cant have children"); shouldn't give exception instead failsafe as
            }
        }
	}
    public void incresesize(int childcount) /*implements whichrunsauto_likethreadwithrunnable i guesss its bogus given current implementation*/ {
        Dino[] biggerarray = new Dino[this.childdinoarray.length *2];

        for(int i = 0; i < biggerarray.length; i++ ){
            //expansion logic
        }
    }
}

interface autoparse{
	public void parsing();
}


class ObjectDino extends Dino implements autoparse /*likerunnableandprocess*/ {


	ObjectDino(Dino[] childdinoarray, String value, String key) {
		super(Jsontype.OBJECT, childdinoarray, value, key);
		//TODO Auto-generated constructor stub
	}
	// specialized behavior for OBJECT nodes
	public void parsing() {
		ObjectDino obj = new ObjectDino();
	}
}

class ArrayDino extends Dino {

	ArrayDino(Dino[] childdinoarray, String value, String key) {
		super(Jsontype.ARRAY, childdinoarray, value, key);
		//TODO Auto-generated constructor stub
	}
    // specialized behavior for ARRAY nodes
}

class ValueDino extends Dino {

	ValueDino(Dino[] childdinoarray, String value, String key) {
		super(Jsontype.VALUES, childdinoarray, value, key);
		//TODO Auto-generated constructor stub
	}
    // specialized behavior for VALUE nodes
}


