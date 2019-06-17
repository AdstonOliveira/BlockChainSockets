package Server;

import Blockchain.Blockchain;
import Server.Threads.ThEsperaCon;
import Server.conectados.ListaConectados;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Suporte-04
 */
public class Server {
   private final ListaConectados lista = new ListaConectados();
   
   private final Blockchain blockchain = new Blockchain();
   private ServerSocket socketServidor;
   private int porta = 666;
   public static volatile boolean parar = false;
   private Funcao funcoes = new Funcao(this);
    
   public Server( int porta ){
       this.porta = porta;
   }
   
    public static void main(String[] args) throws IOException, InterruptedException {
         Server s = new Server( 666 );
         
         System.out.println("Iniciando serviço em: " + 666);
         s.iniciaServico();
         
    }
   
      public boolean iniciaServico(){
         try { 
            this.socketServidor = new ServerSocket( this.porta );
            this.socketServidor.setReuseAddress( true );
            
               ThEsperaCon th = new ThEsperaCon(this);
                  th.start();
               
                  
             System.out.println("Servidor iniciado!!!");         
         return true;
         } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         return false;
         }
      }
      
      public void fecha() throws IOException{ 
         parar = true; // PARAR THREADS SERVIDOR
            try {
                sleep( 1000 );
            } catch (InterruptedException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
                this.socketServidor.close();
                   System.out.println( "Finalizado com sucesso" ); // TESTE
      }
    
    synchronized public static void Scann( Scanner scanner, Server s ){
               //Testes de recebimento
        class ThreadScanner extends Thread{
                       
        public ThreadScanner(Scanner scanner){ 
            this.scanner = scanner;
        }
            public Scanner scanner;
                       
                public void setScaner(Scanner scanner){
                    this.scanner = scanner;
                }
                       
                @Override
                    public void run(){
                        
                        while( scanner.hasNextLine()){
                            System.out.println("SubClass " + scanner.nextLine());
                               // Servidor.imprime(s);
                        }
                    }
                }
                ThreadScanner t = new ThreadScanner(scanner);
                t.start();
    }  
      
      
      
      
      
      
      // --- GETTER SETTER --- 
   public ServerSocket getSocketServidor() {
      return socketServidor;
   }

   public void setSocketServidor( ServerSocket socketServidor ) {
      this.socketServidor = socketServidor;
   }

   public int getPorta() {
      return porta;
   }

   public Funcao getFuncoes() {
      return funcoes;
   }

   public void setFuncoes(Funcao funcoes) {
      this.funcoes = funcoes;
   }

   public void setPorta(int porta) {
      this.porta = porta;
   }

   public static boolean isParar() {
      return parar;
   }

   public static void setParar(boolean parar) {
      Server.parar = parar;
   }

   public ListaConectados getLista() {
      return lista;
   }
    
    
    
}
