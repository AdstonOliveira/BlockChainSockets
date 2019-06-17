package Server;
import Server.conectados.ClienteServidor;
import java.net.Socket;
/**
 * @author Adston at self
*/
public class Funcao {
   private Server servidor;
   public Funcao(Server servidor){ this.servidor = servidor;}

   public boolean login( ClienteServidor cliente ){
      return this.servidor.getLista().adicionarCliente( cliente );
   }

   public boolean lista_usuario(){
       
        for( ClienteServidor conectado : this.servidor.getLista().getLista() )
            conectado.enviarMensagem( this.servidor.getLista().lista_usuarios() );
       return true;
   }

   public boolean transmitir( Socket cliente, String[] msg ){ //Passar posicao 1 do array recebido
       String transmitir = "transmitir:"; // transmitir
       transmitir +=  this.servidor.getLista().identificaSocket(cliente).getNick()+":"; //origem
       String[] destinatarios = this.pegaDestinatarios( msg[1] );
       
       if( destinatarios[0].equalsIgnoreCase("*") ){
           transmitir += "*:" + msg[2];
           this.enviarMsgTodos( transmitir );
       return true;
       }
            for( String destinos : destinatarios ) // se nao é todos
                transmitir += destinos;
              
                    for( int i = 0; i < destinatarios.length; i++ )
                        if( this.servidor.getLista().identificaNick( destinatarios[i]) != null )
                            this.servidor.getLista().identificaNick( destinatarios[i]).enviarMensagem( transmitir );
                    
                    return true;
     //mensagem:destino:msg --- > Chega do cliente      
     // 0        1      2
       //{"transmitir:origem:destino:mensagem"}
       //     0         1       2       3
   }
   //AUXILIARES
   public void enviarMsgTodos(String enviar){
       this.servidor.getLista().enviaTodos( enviar );
   }
   public String[] pegaDestinatarios(String posi1){
       String[] destinos;
       if( posi1.equalsIgnoreCase("*") ){
           destinos = new String[1];
           destinos[0] = "*";
       }else
           destinos = posi1.split(";");
       
       return destinos;
   }


   
}
