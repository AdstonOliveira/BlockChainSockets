package Client;

import Util.SendFile;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * @author Suporte-04
 */
public class Control_Main {
    private Main view;
    private Cliente cliente;
    
    
    public Control_Main(Main view){
        this.view = view;
    }
    
public boolean conected(){
    if(this.getCliente() == null )
        return false;
    if(this.getCliente().getConexao() == null)
        return false;
    
    return this.getCliente().getConexao().isConnected() ;
    
}


    public boolean conectar(String ip, String porta) throws IOException{
        int port = Integer.valueOf(porta);
        if(this.cliente == null)
            this.cliente = new Cliente(ip,port);
        
        this.cliente.conectaServidor();
            
        return this.conected();
    }
    
    public void sendServer(File file) throws IOException{
        SendFile.send(file, this.cliente);
    }
    
    
    
    public void desconectar() throws IOException{
       this.getCliente().finaliza();
       this.cliente = null;
    }
    
    public void sendMsg(String texto){
        this.getCliente().enviarMsg(texto);
    }
    public void sendFile(File file){
        
    }

    public Main getView() {
        return view;
    }

    public void setView(Main view) {
        this.view = view;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    





    
}
