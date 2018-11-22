package Request;

public class IntRequest implements Request {

    private Integer request;

    IntRequest(Object request) {
        this.request = (Integer) request;
    }

    @Override
    public Object generateResponse() {
        return (request+1);
    }
}
