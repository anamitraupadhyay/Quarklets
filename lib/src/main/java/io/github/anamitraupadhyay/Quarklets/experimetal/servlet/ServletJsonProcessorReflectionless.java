package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.AutobindInterface;

/*
* going to be exactly similar to the implementation of the runnable interface implementation
* ServletJsonProcessorReflectionless object = new ServletJsonProcessorReflectionless(new POJO);
* object.bind(); and in the actual pojo class is  going to be as follows:
* public class POJO implements AutobindInterface{
* public int id;
* public string name;
* @override
* public void bind(){
* this.id = getInt("id");
* this.name = getString("name");
*   }
* }
* problem with this implementation is that object.bind() will take parameter as it would have been better this way also in-
* public void bind(){} declaration too, hm any other way out? in runnable and thread case the run method is overridden and the code present there gains thread property or better word is works in a threaded environment.
* ok before the object.bind() i can look into the object.start() idea which will contain the execution steps:
* 1. the requirement of passing the dino tree after parsing built from the tokenization step
* 2. it will search the tree and bind it automatically though the bind code needs to be done in pojo class-
* do i need to do some special oop that is identifying the object?
* o ya since it will implement the AutobindInterface so it will run the that object or accept it and do the operations question is how?*/



public class ServletJsonProcessorReflectionless {
    AutobindInterface object;

    public ServletJsonProcessorReflectionless(AutobindInterface object){
        this.object = object;
    }
    public void method (AutobindInterface object){
        //
    }
}
