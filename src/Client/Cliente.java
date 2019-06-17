package Client;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;
/**
 * @author Palominha
*/
public class Cliente {
    
    private Socket conexao;
    private PrintStream envio;
    private final String endServidor;
    private String nick = "vazio";
    private int porta = 6666;
    public static volatile boolean parar = false;
    private ListaConectados conectados;
    private Funcao funcoes ;
    
    public Cliente( String endServidor, int porta, String nick ){ 
       this.endServidor = endServidor; this.porta = porta; this.nick = this.setNick(nick);   
    }
    public Cliente( String endServidor, int porta ){
       this.endServidor = endServidor; this.porta = porta;
    }
    
    public boolean conectaServidor(){
        try {
            this.conexao = new Socket( this.endServidor, this.porta );
            System.out.println("Conexao" + this.conexao.getLocalAddress());
            
            if( this.getConexao().isConnected() ){
                return this.iniciaCliente();
            }else
                return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    /*
    public boolean transfereListaConectados( String lista ){
       String[] msg = lista.split(":");
       String[] conectados = msg[1].split(";");
       if( this.tela.getBP() != null ){
         this.tela.getBP().setLista( conectados );
         return true;
       }
     return false; 
    }
       
    public void mensagemRecebida( String msg ){
        this.tela.getBP().escreveMensagem( MontaMsgRecebida.montaMsgRecebida(msg) );
    }
    */

    public String getNick(){
        return this.nick;
    }
    
    public boolean iniciaCliente(){
       
      ThRecebeMsg ouvido = new ThRecebeMsg( this );
           ouvido.start();
      
      this.funcoes = new Funcao( this );
      return this.abrirEnvio();
      //this.funcoes.solicitaLogin( this.funcoes.filtraNick(this.nick) );
       //this.tela.abrirBP();
      
    }
    
    private boolean abrirEnvio(){
        try {
            this.envio = new PrintStream( this.conexao.getOutputStream() );
            System.out.println("Envio aberto");
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "erro abertura do PrintrStream" + ex,"Cliente",0);
            return false;
        }
    }
    
    public void finaliza() throws IOException{
       Cliente.parar = true;
       this.enviarMsg("desconectar");
       this.conexao = null;
       this.envio.close();
       this.envio = null;
    }
    
    public void enviarMsg( String msg ){
        this.envio.println(msg);
    }
    public void sendFile(File file){
        this.envio.println(file);
    }

   public String setNick( String nick ){
      if( nick != null && !nick.trim().equalsIgnoreCase("") )
         this.nick = nick;
      
      return this.nick;
   }

    
   public Funcao getFuncoes() {
      return funcoes;
   }

   public void setFuncoes(Funcao funcoes) {
      this.funcoes = funcoes;
   }

   public Socket getConexao() {
      return conexao;
   }

   public void setConexao(Socket conexao) {
      this.conexao = conexao;
   }

   public PrintStream getEnvio() {
      return envio;
   }

   public void setEnvio(PrintStream envio) {
      this.envio = envio;
   }

   public int getPorta() {
      return porta;
   }

   public void setPorta(int porta) {
      this.porta = porta;
   }

   public static boolean isParar() {
      return parar;
   }

   public static void setParar( boolean parar ) {
      Cliente.parar = parar;
   }

   public ListaConectados getConectados() {
      return conectados;
   }

   public void setConectados(ListaConectados conectados) {
      this.conectados = conectados;
   }
    
    
    
    
    
    
    
    
    
    
        

    
}
