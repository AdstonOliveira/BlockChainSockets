package Server.conectados;

import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Adston at self
*/
public class ListaConectados {

   private ArrayList<ClienteServidor> lista = new ArrayList();
   
   //FUNCOES DA LISTA
   public boolean adicionarCliente( ClienteServidor cliente ){
      if( this.nomeDuplicado( cliente.getNick() ) )
         return false;
      
      this.lista.add( cliente );
      return true;
   }

   private boolean nomeDuplicado( String nome ){
      for(ClienteServidor conectado : this.lista)
         if( nome.equalsIgnoreCase(conectado.getNick() ) )
            return true;
      
      return false;
   }
   
   public String lista_usuarios(){
      String lista_usuarios = "lista_usuarios:";
      if( this.lista.size() > 0 )
         for(ClienteServidor conectado : this.lista)
            lista_usuarios += conectado.getNick()+";";
      else
         lista_usuarios += "";
      
   return lista_usuarios;
   }

   public ClienteServidor identificaNick( String nick ){
       for( ClienteServidor cliente : this.getLista() )
           if( cliente.getNick().equalsIgnoreCase(nick) )
               return cliente;
       
       return null;
   }
   
   public void enviaTodos( String msg ){
       if( this.lista.size() > 0 )
          for(ClienteServidor c : this.getLista()) 
              c.enviarMensagem(msg);
   }
   
   public ClienteServidor identificaSocket( Socket socket ){
       if( this.getLista().size() > 0 )
           for( ClienteServidor cliente : this.getLista() )
               if(cliente.getSocket() == socket)
                   return cliente;
           
       return null;
   }
   
   
   
   //######## Getter and Setter
   public ArrayList<ClienteServidor> getLista(){ return lista; }
   public void setLista(ArrayList<ClienteServidor> lista) { this.lista = lista; }



}