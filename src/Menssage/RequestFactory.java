package Menssage;

import Exceptions.UnsuportTypeException;

import java.io.IOException;
import java.io.ObjectInputStream;

public class RequestFactory {

    private boolean requestIsInt(Object in){
        try {
            Integer aux = (Integer) in;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    private boolean requestIsChar(Object in){
        try {
            Character aux = (Character) in;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    private boolean requestIsString(Object in){
        try {
            String aux = (String) in;
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Request getRequest(ObjectInputStream in) throws UnsuportTypeException {
        Object aux = "";
        try {
            aux = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (requestIsString(aux))
            return new StringRequest(aux);
        throw new UnsuportTypeException("Tipo não permitido");
    }
}
