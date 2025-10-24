package io.github.anamitraupadhyay.Quarklets.experimetal.servlet;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import io.github.anamitraupadhyay.Quarklets.experimetal.parser.AutoBinder;
import io.github.anamitraupadhyay.Quarklets.experimetal.datastructure.Dino;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.DataHandlerFromServlet;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.SimpleParser;
import io.github.anamitraupadhyay.Quarklets.experimetal.parser.Token;
import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.Tokenizer;
//import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.AutobindInterface;

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
* do I need to do some special oop that is identifying the object?
* o ya since it will implement the AutobindInterface so it will run the that object or accept it and do the operations question is how?*/



public class ServletJsonProcessorReflectionless {
    //AutoBinder autobindobj;
    HttpServletRequest request;

    public ServletJsonProcessorReflectionless(HttpServletRequest request){
        this.request = request;
    }

    public <T extends AutoBinder> T binder(T pojo){
            // Step 1: HttpServletRequest → StringBuilder
            StringBuilder jsonData = DataHandlerFromServlet.stringbuilderparse(request);
            
            // Step 2: Find JSON start position (handle BOM/cleanup)
            int jsonStart = DataHandlerFromServlet.findJsonStart(jsonData);
            
            // Step 3: StringBuilder → Tokens
            Tokenizer tokenizer = new Tokenizer();
            List<Token> tokens = tokenizer.JsonParse(jsonData, jsonStart);
            
            // Step 4: Tokens → Dino object tree
            SimpleParser parser = new SimpleParser();
            Dino jsonTree = parser.parse(tokens);
            
            pojo.setroot(jsonTree);//setroot as the bind() stays clean in actual usage
            
            return pojo;
    }
}
