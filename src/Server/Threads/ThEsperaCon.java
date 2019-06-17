package Server.Threads;
import Server.Server;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Adston at self
*/
public class ThEsperaCon extends Thread{
   private final Server servidor;
   
   public ThEsperaCon( Server servidor ){
      this.servidor = servidor;
       System.out.println("Esperando conexoes");
   }
   
   @Override
      public void run(){
          System.out.println("waiting " + this.servidor.getSocketServidor().getLocalSocketAddress());
         while( !Server.parar ){
             Socket socket;
             try {
                 socket = this.servidor.getSocketServidor().accept();
                 System.out.println("Socket Conectado: " + socket);
                 new ThLeSocket( socket, this.servidor ).start();
                 
             } catch (IOException ex) {
                 Logger.getLogger(ThEsperaCon.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
         this.interrupt();
         
      }
   
   
}
