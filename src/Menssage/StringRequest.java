package Menssage;

import java.io.IOException;
import java.io.ObjectInputStream;

public class StringRequest implements Request {

    private String request;

    public StringRequest(Object request) {
        this.request = (String) request;
    }

    public String invertCase(String s){
        StringBuilder new_string = new StringBuilder();
        for (Character c : s.toCharArray()) {
            c = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
            new_string.append(c);
        }
        return new_string.toString();
    }
    @Override
    public Object generateResponse() {
        return invertCase(request);
    }
}
