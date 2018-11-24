package Exceptions;

public class BadRequestException extends Exception {

    public BadRequestException() {
        super("Menssagem não compativel com o tipo");
    }
}
