import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerTCP {
    public static void main(String[] args) {
        try {
            int port = 12345;
            ServerSocket servidor = new ServerSocket(port);
            System.out.println("Servidor criado com sucesso na porta "+ port);
            while(true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                ObjectInputStream response = new ObjectInputStream(cliente.getInputStream());
                String msg_recived = (String)response.readObject();

                ObjectOutputStream msg_to_send = new ObjectOutputStream(cliente.getOutputStream());
                msg_to_send.flush();
                msg_to_send.writeObject(msg_recived.toLowerCase());
                msg_to_send.close();
                cliente.close();
            }
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
