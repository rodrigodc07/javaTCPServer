package Request;

public class StringRequest implements Request {

    private String request;

    StringRequest(Object request) {
        this.request = (String) request;
    }

    private String invertString(String s){
        StringBuilder new_string = new StringBuilder();
        char[] charArray = s.toCharArray();
        char c;
        for (int i = s.length()-1; i >= 0;i--) {
            c = charArray[i];
            new_string.append(c);
        }
        return new_string.toString();
    }
    @Override
    public Object generateResponse() {
        return invertString(request);
    }
}
