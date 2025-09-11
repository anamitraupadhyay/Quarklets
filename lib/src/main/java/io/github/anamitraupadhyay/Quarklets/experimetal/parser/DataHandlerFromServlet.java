package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

import io.github.anamitraupadhyay.Quarklets.experimetal.processinglogic.UnknownCharacterEncountered;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class DataHandlerFromServlet {

    public static StringBuilder stringbuilderparse(HttpServletRequest httpServletRequestobject){
        StringBuilder builderobj = new StringBuilder();
        try (BufferedReader reader = httpServletRequestobject.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                builderobj.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builderobj;
    }

    public static int findJsonStart(StringBuilder builderobj) { //i have studied that json start with bom like''' and end with ''' and many others need to clean it even further
        for (int i = 0; i < builderobj.length(); i++) {
            char c = builderobj.charAt(i);
            if (c == '{' || c == '[') {
                return i; // Found start of JSON
            }
        }
        throw new IllegalArgumentException(new UnknownCharacterEncountered());
    }

    public static StringBuffer stringbufferparse(HttpServletRequest httpServletRequestobject){
        StringBuffer obj = new StringBuffer(null);
        return obj;
    }
    public static StringBuffer stringbufferthreadedparse(HttpServletRequest httpServletRequestobject){return new StringBuffer(null);}
}
