import Menssage.Request;
import Menssage.RequestFactory;
import Menssage.StringRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    public static void main(String[] args) {
        try {
            int port = 12345;
            ServerSocket servidor = new ServerSocket(port);
            RequestFactory requestFactory = new RequestFactory();
            System.out.println("Servidor criado com sucesso na porta "+ port);
            while(true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                ObjectInputStream request = new ObjectInputStream(cliente.getInputStream());
                Request msg_received = requestFactory.getRequest(request);

                ObjectOutputStream response = new ObjectOutputStream(cliente.getOutputStream());
                response.flush();
                response.writeObject(msg_received.generateResponse());
                response.close();
                cliente.close();
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
