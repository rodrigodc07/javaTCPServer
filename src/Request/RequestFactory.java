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
        char header = readLine.charAt(0);
        switch (header) {
            case '1':
                return "String";
            case '2':
                return "Integer";
            case '3':
                return "Char";
            default:
                throw new UnsuportTypeException("Tipo não permitido");
        }
    }

    private String getMessage(BufferedReader in) throws IOException {
        StringBuilder message = new StringBuilder();
        char sentence;
        boolean hasNext = true;
        while (hasNext) {
            sentence = (char) in.read();
            if (sentence == '\uFFFF') {
                hasNext = false;
            } else {
                message.append(sentence);
            }
        }
        return message.toString();
    }
    public Request getRequest(BufferedReader in) throws UnsuportTypeException, BadRequestException {
        String type;
        try {
            String message = getMessage(in);
            type = getRequestType(message);
            message = message.substring(2);
            switch (type) {
                case "String":
                    return new StringRequest(message);
                case "Integer":
                    return new IntRequest(message);
                case "Char":
                    return new CharRequest(message);
                default:
                    throw new UnsuportTypeException("Tipo não permitido");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new BadRequestException("Tipo não permitido");
    }

}
