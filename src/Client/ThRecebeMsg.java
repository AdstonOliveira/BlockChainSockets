package Client;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * @author Palominha
 */
public class ThRecebeMsg extends Thread{
    Scanner scanner;
    Cliente cliente;
    public ThRecebeMsg( Cliente conectado ){
       this.cliente = conectado; 
       try {
            this.scanner = new Scanner( conectado.getConexao().getInputStream() );
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro abrir Scanner" + ex, "Cliente", 0);
        }
    }
    @Override
        public void run(){
            while( !Cliente.parar ){
                while( this.scanner.hasNextLine() )
                   this.identificaFuncao( scanner.nextLine() );
        
                     if( !this.scanner.hasNext() ){
                        JOptionPane.showMessageDialog( null,"Servidor desconectou");
                        try {
                            this.cliente.finaliza();
                            this.interrupt();
                        } catch (IOException ex) {
                            this.interrupt();
                        }
                     }
            }
            this.interrupt();
        }
    
        
        
        public void identificaFuncao( String recebida ){
           
           String[] funcao = recebida.split(":");
           
           /*    switch ( funcao[0] ) {
                  case "login" :
                     if( this.cliente.getFuncoes().respostaLogin( recebida ) ){
                         this.cliente.getTela().abrirBP();
                     }else{
                         this.cliente.getTela().dispose();
                         this.interrupt();
                         new TelaCliente().setVisible(true);
                     }
                  break;
                  
                  case "lista_usuarios" :
                     this.cliente.getFuncoes().lista_recebida( recebida );
                     this.cliente.transfereListaConectados( recebida );
                  break;
                  
                  case "transmitir":
                      this.cliente.mensagemRecebida( recebida );
                      break;
                      
                  case " O Servidor Desconectou" :
                      this.cliente.getTela().getBP().dispose();
                      this.cliente.getTela().dispose();
                      
                      break;
                default :
                  JOptionPane.showMessageDialog( this.cliente.getTela(), recebida, "Mensagem recebida do Servidor", 1 );
               }
           */
        }
        
        
        
        
        
        
    
}
