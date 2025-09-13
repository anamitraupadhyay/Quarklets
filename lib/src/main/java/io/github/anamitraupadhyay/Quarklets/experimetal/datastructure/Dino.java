package io.github.anamitraupadhyay.Quarklets.experimetal.datastructure;


public abstract class Dino{
	
	
	Jsontype jsonenum;

    /**
     * in order to parse the Dino type array to extend and copy its contents to the biggerarray that is making the array suit for parsing using loops.
     * ok os approach taken now is that deciding which will have just key i.e. object and array, value will have both key and value set
     * 1st goal is to stabilize the value part with (uptill decided) with hybrid combination of factory and observer patterns
     * and to analyze that if there is a need to trim down storage taken by default declaration may be value key array childcount
     */
	Dino[] childdinoarray;

	public String value;

	String key;

	//char [] defaultarray; not required as the Strings will handle it

	int childcount;


	Dino(Jsontype jsonenum, /*Dino[] childdinoarray,*/ String value, String key) {
		this.jsonenum = jsonenum;
		//this.childdinoarray = childdinoarray;
		this.value = value;
		this.key = key;

		if (jsonenum.getcanhavechildren()) { // written in constructor as to init memory efficiently
			this.childdinoarray = new Dino[2];
		} else {
			this.childdinoarray = null;
		}
	}

	public void addchild(Dino child) throws IllegalArgumentException
	{
		//while( this.childcount<this.childdinoarray.length ) { is so wrong as it was designed previously, now in updated design the inner if handles that this is good when we know the size which is possible but not easy to implement, whats easy is that to count total characters there in buffer
            if (this.jsonenum.getcanhavechildren()) { //child.jsonenum is wrong logically as checking childs ability to check can have child or not
                /*this.childdinoarray[this.childcount] = child; logically bad position
                this.childcount++;*/
                if(this.childcount >= this.childdinoarray.length){
                   incresesize(); //so void non-static method eith no parameter can also be called like an api just to do the job as here its just about increasing the size and as of its in the class it can take no parameter and still increase
                }
                this.childdinoarray[this.childcount] = child;
                this.childcount++;
            } else {
                //throw new IllegalAccessException(child.jsonenum + "cant have children"); shouldn't give exception instead failsafe shold be to continue looking forward or returns something ok but how its void and , wait i can throw an exception just not extra letters returns something like false flag of the enum and i got to know access one is wrong correct is argument?!
                throw new IllegalArgumentException(child.jsonenum +"");
            }
        //}
	}
    public void incresesize(/*Dino[] child*/) /*implements whichrunsauto_likethreadwithrunnable i guesss its bogus given current implementation*/ {//can keep the void return type as i have to set the reference variable to created biggerarray and does the memory leak happen if i dont handle the old pointed array
        //method call to increase the size? to increasesize how to call it
        Dino[] biggerarray = new Dino[this.childdinoarray.length *2];

        for(int i = 0; i <this.childcount /*this.childdinoarray.length*/; i++ ){//look over the below comment its decided that this loop will run to copy the content as it will happen as per the runtimely updated childcount but does that array stores the data in somehwere to later count fotr it i guess not so need to store the value in some variable before storing and o yea its get stored
            //expansion logic i.e copying the existing childdinoarray over the biggerarray and then changing the childdinoarray
            //so the loop will run upto this.childdinoarray.length not biggerarray.length
            //now the main problem now i have to mini parse to copy the content or any other logic will work?
            biggerarray[i] = this.childdinoarray[i];
        }
        this.childdinoarray = biggerarray; //replaced the old array need memory clean up btw of the old childdinoarray?
    }

    public abstract Dino findChildByKey(String key);
    
    // Public getter for child count
    public int getChildCount() {
        return childcount;
    }
}

interface autoparse{
	public void parsing();
}


