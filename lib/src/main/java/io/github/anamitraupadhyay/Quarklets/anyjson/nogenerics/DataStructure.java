package io.github.anamitraupadhyay.Quarklets.anyjson.nogenerics;

import static io.github.anamitraupadhyay.Quarklets.essentials.JsonUtils.parse;  // Only a type can be imported.happened when i didnt added the method i wanted which was static in nature
                                                                                // io.github.anamitraupadhyay.Quarklets.essentials
                                                                                // resolves to a packageJava(268435843)

//so what does the static imports do: ok after i removed the static word from the it gave the the red line showing it cant be resolved as i was importing the static 

public class DataStructure {
    //why did the filename changed with public class name? for the fact that it has static import or it has public class as public can be one per java file
    //observation after i made a file with different name and no static import only a public class with different name: public class phenomenon yup that marks it
}

class Patternmatch {
    
}

interface StringType {
    public abstract String StringPojo();
}

interface CharArrayType {
    public abstract char[] CharPojo();
}

interface StringBufferType {
    public abstract StringBuffer SbufferPojo();
}

interface StringBuilder {
    public abstract StringBuilder SBuilderpojo();
}

class DatafromrequestservletCharArrayBuilder /*implements CharArrayType*/ {
    
    char[] obj;

    CharArrayType target;

    public char[] SBuilderpojo() {
        //char[] obj;
        return this.obj; //why local variable not initialized? the moment i made it instance the error vanished
    }

    DatafromrequestservletCharArrayBuilder(CharArrayType target) {
        //pojo class implements CharArrayType as i know it automatically is that interface object so
        this.target = target;
    }
}