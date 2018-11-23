package Request;

public class IntRequest implements Request {

    private Integer request;

    IntRequest(String request) {
        this.request = Integer.parseInt(request) ;
    }

    @Override
    public Object generateResponse() {
        return (request+1);
    }
}
