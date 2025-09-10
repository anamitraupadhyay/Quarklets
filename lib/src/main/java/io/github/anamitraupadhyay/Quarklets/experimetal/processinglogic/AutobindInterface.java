package io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic;

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
}