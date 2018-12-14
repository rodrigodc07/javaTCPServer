package Message;

import Exceptions.MalformattedRequestException;

import java.io.BufferedReader;
import java.io.IOException;

public class RawMessage {
    private String raw_message;
    private final int MAX_LENGTH=512;

    public RawMessage(BufferedReader in) throws MalformattedRequestException {
        StringBuilder message = new StringBuilder();
        char sentence;
        int count = 0;

        boolean hasNext = true;
        try {
            do {
                count++;
                sentence = (char) in.read();
                if (sentence == '\0') {
                    hasNext = false;
                } else {
                    message.append(sentence);
                }
                if(count > MAX_LENGTH)
                    throw new MalformattedRequestException();
            } while (hasNext);
            raw_message = message.toString();
        }catch (IOException e){
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
