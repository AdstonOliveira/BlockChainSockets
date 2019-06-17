package Server.Threads;
import Server.Server;
import Server.conectados.ClienteServidor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Adston at self
*/
public class ThLeSocket extends Thread{
   private Socket socket;
   private Scanner scanner;
   private volatile boolean stop=false;
   private final Server servidor;
   
   public ThLeSocket( Socket socket, Server servidor ){
      this.socket = socket;
      this.servidor = servidor;
   }
   
   @Override
   public void run(){
      try {
         this.scanner = new Scanner( socket.getInputStream() );
         System.out.println("Scanner aberto, Server Esperando MSGdo Cliente" + socket);
      } catch (IOException ex) {
          System.out.println("Erro ao abrir este scanner" + socket);
      }
    //  while( !Servidor.parar  )
          try {
              this.lerScanner();
          } catch (IOException ex) {
              Logger.getLogger(ThLeSocket.class.getName()).log(Level.SEVERE, null, ex);
          }
        
   }
   
   public synchronized void lerScanner() throws IOException{
         while( scanner.hasNextLine() ){ // receber um arquivo
             String valor = scanner.nextLine();
            this.encaminharFuncao( valor, this.socket );
            
            if( !scanner.hasNext() ){
                this.stop = true;
                System.out.println("Cliente desconectado"); // implementar desconexao
                this.interrupt();
            }   
         }
   }
   // Deve encaminhar para o pool para ser adicionada a rede
   //Deve passar toda a blockchain aos clientes
    
      private void encaminharFuncao( String recebida, Socket socketEnviou ) throws IOException{
         
          System.out.println("Encaminhar Funcao");
          String[] identificaFuncao = recebida.toLowerCase().split("::");
          
          switch (identificaFuncao[0]){
              case ("desconectar"):
                socketEnviou.close();
               break;
              case "file":
                this.receiveFile();
              break;
              default:
                  System.out.println("Funcao indefinida");
                  break;
          }
         //System.out.println(recebida);
          
          /*System.out.println("Oieeee");
         if( this.checaTamanho(identificaFuncao) ){
            switch ( identificaFuncao[0] ){
               case "login" :
                   System.out.println("Estou no login");
                   String[] erroLogin = { "login:false", "Usuario em uso, utilize outro" };
                   ClienteServidor cliente;
                  if(this.loginValido(identificaFuncao[1])){
                     cliente = new ClienteServidor( this.socket, identificaFuncao[1] );
                     if( this.servidor.getFuncoes().login( cliente ) ){
                        cliente.enviarMensagem( "login:true" );
                        cliente.enviarMensagem( "Usuario registrado com sucesso!!!" );
                        this.servidor.getFuncoes().lista_usuario();
                     }else{
                         try {
                             this.enviarErro( this.socket, erroLogin );
                         } catch (IOException ex) {
                             Logger.getLogger(ThLeSocket.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                  }else{
                      erroLogin[1] = "MensagemLogin não atende aos parametros";
                        try {
                            this.enviarErro( socket, erroLogin );
                        } catch (IOException ex) {
                            Logger.getLogger(ThLeSocket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  }
               break;
               case "mensagem" :
                   this.servidor.getFuncoes().transmitir( socketEnviou, identificaFuncao );
               break;
               case "sair":
                   //implementar se possivel
               break;
               default :
                   String[] erroFuncao = {"comando não idenificado","Verifique o formato de seu envio"};// informar erro;
                   this.enviarErro(socket, erroFuncao);
            }
         }else{
            String[] erroFuncao = {"String recebida em formatdo invalido","Verifique o formato de seu envio"};// informar erro;
            this.enviarErro(socket, erroFuncao);
         }*/
      }
   public void receiveFile() throws IOException {
    int bytesRead;
    DataInputStream clientData;
        try {
           clientData = new DataInputStream(socket.getInputStream());

                String fileName = clientData.readUTF();
                String caminhoCompleto = ".\\teste\\"+ fileName;
                OutputStream output = new FileOutputStream((caminhoCompleto));
                long size = clientData.readLong();
                byte[] buffer = new byte[1024];

                while (size > 0 && (bytesRead = clientData.read(buffer, 0,
                        (int) Math.min(buffer.length, size))) != -1) {
                                output.write(buffer, 0, bytesRead);
                    size -= bytesRead;
                }
                output.close();

            System.out.println("Arquivo " + fileName + " recebido.");
        
       } catch (IOException ex) {
           Logger.getLogger(ThLeSocket.class.getName()).log(Level.SEVERE, null, ex);
       }


}
   public boolean loginValido(String nickAvaliar){
       String avaliar = nickAvaliar.trim();
       if( avaliar.equals("") )
           return false;
       return true;
   }
   
   public boolean checaTamanho( String[] recebida ){
       switch ( recebida[0] ){
           case "login":
               if(recebida.length > 2)
                   return false;
           return true;
           case "mensagem" :
               if(recebida.length > 3)
                   return false;
           return true;
           default :
               return true;
       }
   }
   
   public void enviarErro(Socket socket, String[] erro) throws IOException{
       PrintStream EnvioErro = new PrintStream(socket.getOutputStream());
       EnvioErro.println(erro[0]);
       EnvioErro.println(erro[1]);
   }
   
   
   
   
   
   
}