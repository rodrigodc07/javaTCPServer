package Request;

import Exceptions.BadRequestException;
import Exceptions.UnsuportTypeException;

import java.io.BufferedReader;
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

    private String getRequestType(String readLine) throws UnsuportTypeException {
        switch (readLine) {
            case "1":
                return "String";
            case "2":
                return "Integer";
            case "3":
                return "Char";
            default:
                throw new UnsuportTypeException("Tipo não permitido");
        }
    }

    public Request getRequest(BufferedReader in) throws UnsuportTypeException, BadRequestException {
        String type;
        try {
            type = getRequestType(in.readLine());
            String aux = in.readLine();
            switch (type) {
                case "String":
                    return new StringRequest(aux);
                case "Integer":
                    return new IntRequest(aux);
                case "Char":
                    return new CharRequest(aux);
                default:
                    throw new UnsuportTypeException("Tipo não permitido");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new BadRequestException("Tipo não permitido");
    }

}
