package Menssage;

import java.io.ObjectInputStream;

public class RequestFactory {

    public Request getRequest(ObjectInputStream in){
        return new StringRequest(in);
    }
}
