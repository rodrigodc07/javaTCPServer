package Request;

import Exceptions.BadRequestException;
import Exceptions.UnsuportTypeException;
import Message.RawMessage;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestFactory {

    public Request getRequest(BufferedReader in) throws UnsuportTypeException, BadRequestException {
        try {
            RawMessage raw_message = new RawMessage(in);
            String type = raw_message.getRequestType();
            String message = raw_message.getBody();
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
