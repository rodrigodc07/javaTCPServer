import Request.Request;
import Request.RequestFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    public static void main(String[] args) {
        try {
            int port = 12345;
            ServerSocket server = new ServerSocket(port);
            RequestFactory requestFactory = new RequestFactory();
            System.out.println("Servidor criado com sucesso na porta "+ port);
            while(true) {
                Socket cliente = server.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                BufferedReader request_stream = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintStream response_stream = new PrintStream(cliente.getOutputStream());
                response_stream.flush();
                try {
                    Request request = requestFactory.getRequest(request_stream);
                    Object response = request.generateResponse();
                    response_stream.println(response);
                }catch (Exception e){
                    response_stream.println(e);
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
