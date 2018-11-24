package Exceptions;

public class MalformattedRequestException extends Exception {

    public MalformattedRequestException() {
        super("Menssagem não esta nos padrões definidos");
    }
}
