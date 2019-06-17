package Client;

import java.util.ArrayList;

/**
 * @author Adston at self
*/
public class Funcao {

   Cliente cliente;
   public boolean permiteLogin = false;
   
   public Funcao( Cliente cliente ){this.cliente = cliente;}
      
   public void solicitaLogin( String nick ){ 
      this.enviar( "login:"+( nick ) );
   }
   public void enviar( String msg ){ 
      this.cliente.getEnvio().println( msg ); 
   }
   public void enviarMensagem( String mensagem ) { this.enviar(/*"mensagem:" + */mensagem); } // VERIFICAR PASSAGEM METODO DUPLICADO
   
   public void lista_recebida( String lista ) {
      
      if( this.cliente.getConectados() == null ){
         ListaConectados nova = new ListaConectados( this.cliente );
         this.cliente.setConectados( nova );
      }
         String[] conec = lista.split(":");
         String isolados = conec[1];
         ArrayList<String> list = new ArrayList();
            
            for( String each : isolados.split(";") )
               list.add( each );
         
                  this.cliente.getConectados().setConectados( list );
   }
   public boolean respostaLogin( String recebida ){ 
      if( recebida.split(":")[1].equalsIgnoreCase("true") ){
         //this.cliente.getTela().abrirBP();
         return true;
      }
      return false; 
   }
   
   
   
   
  //AUXILIARES
    public String filtraNick( String nick ){
       if( nick.trim().equalsIgnoreCase("") )
          nick = "vazio";
       
     return nick.trim();
    }
      
      
      
      
}
