import Request.Request;
import Request.RequestFactory;

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

                ObjectInputStream request_stream = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream response_stream = new ObjectOutputStream(cliente.getOutputStream());
                response_stream.flush();
                try {
                    Request request = requestFactory.getRequest(request_stream);
                    Object response = request.generateResponse();
                    response_stream.writeObject(response);
                }catch (Exception e){
                    response_stream.writeObject(e.getMessage());
                }
                finally {
                    response_stream.close();
                }
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
