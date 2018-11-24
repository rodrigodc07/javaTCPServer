package Message;

import Exceptions.MalformattedRequestException;
import Exceptions.UnsuportTypeException;

import java.io.BufferedReader;
import java.io.IOException;

public class RawMessage {
    private String raw_message;

    public RawMessage(BufferedReader in) throws MalformattedRequestException {
        StringBuilder message = new StringBuilder();
        char sentence;
        boolean hasNext = true;
        try {
            do {
                sentence = (char) in.read();
                if (sentence == '\uFFFF') {
                    hasNext = false;
                } else {
                    message.append(sentence);
                }
            } while (hasNext);
            raw_message = message.toString();
        }catch (Exception e){
            throw new MalformattedRequestException();
        }
    }

    public String getRequestType() {
        char header = raw_message.charAt(0);
        switch (header) {
            case '1':
                return "String";
            case '2':
                return "Integer";
            case '3':
                return "Char";
            default:
                return "-1";
        }
    }

    public String getBody() {
        return raw_message.substring(2);
    }
}
