package Client;
import java.util.ArrayList;
/**
 * @author Adston
*/
public class ListaConectados {
   private Cliente cliente;
   private ArrayList<String> conectados;
   
   public ListaConectados( Cliente cliente ){ this.cliente = cliente; }
   
   public void setConectados( ArrayList<String> conectados ){ this.conectados = conectados; }

   public ArrayList<String> getConectados() { return conectados; }

   
   
   
   
   
   
   
   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }
   
      
   
   
   
   
   
   
   
}
