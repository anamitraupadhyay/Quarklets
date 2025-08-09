//package main.java;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import main.java.InputPOJO;

import java.io.IOException;
import java.io.PrintWriter;

import Quarklets.essentials.*;

@WebServlet("/user")
public class UserServlet extends BaseServlet {

    /*@JsonBody
    public static class UserRequest {
        public String name;
        public int age;
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        /*
         * UserRequest user = parseBody(req, UserRequest.class);
         * writeJson(res, Map.of("message", "User added: " + user.name));
         */
        //InputPOJO obj = new InputPOJO().jsonparse(HttpServletRequest req);
        /*BaseServlet obj = new ; cant find a wayout stuck and forgotten how to and not to make objects of classes and which classes permit here does extending httpservlet permit or not
        UserServlet() obj = new UserServlet();*/
        //after declaring static method to jsonparse and extending that i guess its best to just call the method and it will return and object will be stored in something but doesnt static method might fail idk what am asking right now, ok btw i return jsonreader object 
        //JsonObject obj = jsonparse(req);
        //JsonReader obj1 = jsonparse(req);
    }
}
