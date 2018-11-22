package Request;

public class CharRequest implements Request {

    private Character request;

    CharRequest(Object request) {
        this.request = (Character) request;
    }

    private char invertCase(Character c){
        return Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
    }
    @Override
    public Object generateResponse() {
        return invertCase(request);
    }
}
