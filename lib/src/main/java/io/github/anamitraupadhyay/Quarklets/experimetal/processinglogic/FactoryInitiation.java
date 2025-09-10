package io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic;

import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ValueDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ObjectDino;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.ArrayDino;

//factory pattern
public class FactoryInitiation{
    public static void  toinit(Object obj){ //later to replaced like runnable interface like thread class api executes any class implementing it
        if(obj instanceof ValueDino){

        } else if (obj instanceof ArrayDino) {

        } else if (obj instanceof ObjectDino) {

        } else {
            throw new IllegalArgumentException(new UnknownCharacterEncountered());
        }
    }
}