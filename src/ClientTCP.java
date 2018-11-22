import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class ClientTCP {
  public static void main(String[] args) {
    try {
      Socket cliente = new Socket("localhost",12345);

      ObjectOutputStream request = new ObjectOutputStream(cliente.getOutputStream());
      request.flush();
      request.writeObject("Carlos");

      ObjectInputStream response = new ObjectInputStream(cliente.getInputStream());
      String data_atual = (String)response.readObject();
      JOptionPane.showMessageDialog(null,"Data recebida do servidor:" + data_atual.toString());
      response.close();
      System.out.println("Conexão encerrada");
    }
    catch(Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }
}