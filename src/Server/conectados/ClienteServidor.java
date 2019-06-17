package Server.conectados;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
/**
 * @author Adston at self
*/
public class ClienteServidor {

   private Socket socket; 
   private String nick;
   private PrintStream envioCliente;

   public ClienteServidor( Socket socket, String nick ){ this.socket = socket; this.nick = nick; this.criarSaida(); }

   public boolean criarSaida(){
      try {
         this.envioCliente = new PrintStream( this.socket.getOutputStream() );
         return true;
      } catch (IOException ex) {
         return false;
      }
   }




   public void enviarMensagem( String msg ){
      this.envioCliente.println( msg );
       //System.out.println(msg);
   }

   public Socket getSocket() {
      return socket;
   }

   public void setSocket(Socket socket) {
      this.socket = socket;
   }

   public String getNick() {
      return nick;
   }

   public void setNick(String nick) {
      this.nick = nick;
   }
      
      
      
   
}
