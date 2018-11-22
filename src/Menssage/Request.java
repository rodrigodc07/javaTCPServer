package Menssage;

import java.io.Serializable;

public interface Request extends Serializable {
    public Object generateResponse();
}
