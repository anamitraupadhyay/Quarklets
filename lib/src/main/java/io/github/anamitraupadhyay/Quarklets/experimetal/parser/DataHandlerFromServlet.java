package io.github.anamitraupadhyay.Quarklets.experimetal.parser;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class DataHandlerFromServlet {

    public static StringBuilder stringparse(HttpServletRequest httpServletRequestobject){
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
}
