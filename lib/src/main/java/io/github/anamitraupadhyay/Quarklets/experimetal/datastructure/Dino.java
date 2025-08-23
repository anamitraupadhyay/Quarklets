package io.github.anamitraupadhyay.Quarklets.experimetal.datastructure;

public class Dino{
	
	public enum Jsontype{
		OBJECT(false, true), 
		ARRAY(true, true), 
		VALUES(true, false);

		private final boolean keycanbenull;
		private final boolean canhavechildren;

		Jsontype(boolean keycanbenull, boolean canhavechildren){
			this.keycanbenull = keycanbenull;
			this.canhavechildren = canhavechildren;
		}

		public boolean getkeycanbenull(){ return keycanbenull;}
		public boolean getcanhavechildren(){ return canhavechildren;}
	}

	Jsontype jsonenum;

	Dino[] childdinoarray;

	String value;

	String key;

	//char [] defaultarray; not required as the Strings will handle it

	int childcount;


	Dino(Jsontype jsonenum, Dino[] childdinoarray, String value, String key){
		this.jsonenum = jsonenum;
		this.childdinoarray = childdinoarray;
		this.value = value;
		this.key = key;
	}

}

interface autoparse{
	public Dino parsing();
}

interface stack {
	public Dino Stackops();
}

class DinoStack /*extends Dino*/{

	DinoStack(Jsontype jsonenum, Dino[] childdinoarray, String value, String key){
		//jdk version min 23 needed to call super after some method calls for config
		super(Jsontype jsonenum, Dino[] childdinoarray, String value, String key);
	}

	public Dino parsing(){
	}
}

class DinoTree /*extends Dino*/{

}

class parsing /*extends Dino*/ implements autoparse, stack, {

}



abstract class DinoTemplate{
	// basic template for the below 3 containing enums for the subclasses
}

public class ObjectDino extends DinoTemplate implements likerunnableandprocess{
    // specialized behavior for OBJECT nodes
}

public class ArrayDino extends DinoTemplate {
    // specialized behavior for ARRAY nodes
}

public class ValueDino extends DinoTemplate {
    // specialized behavior for VALUE nodes
}


