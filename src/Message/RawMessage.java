package Message;

import Exceptions.UnsuportTypeException;

import java.io.BufferedReader;
import java.io.IOException;

public class RawMessage {
    private String raw_message;

    public RawMessage(BufferedReader in) throws IOException {
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
        raw_message = message.toString();
    }

    public String getRequestType() throws UnsuportTypeException {
        char header = raw_message.charAt(0);
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

    public String getBody() {
        return raw_message.substring(2);
    }
}
