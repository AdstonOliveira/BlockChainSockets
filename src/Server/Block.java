package Server;

import Util.StringUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Suporte04
 */
public class Block implements Serializable{
    public final String previousHash; // Hash anterior
    
    private long timeStamp; //data atual 
    public String hash = "incomplent block"; // Hash do atual
    private ArrayList<File> dados = new ArrayList(); //Dado a ser adicionado ao bloco
    private int nonce = 0;
    private final int amount_transactions = 5;
    private final Blockchain blockchain;
    private volatile boolean aprovade = false;
    //Blocos seguintes *****
    
  /*  public Block(){
        this.timeStamp = new Date().getTime();
        this.previousHash = "Dont have";
        this.blockchain = new Blockchain();
    }*/
    
    
    public Block( Blockchain blockchain, String previousHash  ) {
        this.blockchain = blockchain;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        
    }
    
    //Bloco Genesis *****
    public Block(Blockchain blockchain){
        this.blockchain = blockchain;
        this.previousHash = "Dont have";
        this.timeStamp = new Date().getTime();
    }
    
    //VERIFICA SE O BLOCO SE ENCONTRA COMPLETO.
    public boolean isFull(){
        return this.dados.size() >= this.amount_transactions;
    }
    
    public boolean add_transation( File file ){
        if( !this.isFull() ){
            System.out.println("Não esta cheio");
            this.dados.add(file);
            JOptionPane.showMessageDialog(null,"Agora Este bloco tem: " + this.dados.size() + " transacoes" );
            return true;    
        }else{
            System.out.println("Este bloco esta cheio, calculando hash");
            this.calculate_hash(); // Dsitribuidos
            return false;
        }        
    }
    
    public boolean calculate_hash(){
        this.mineBlock(5);
        //Enviar alerta para consenso
        return true;
    }
    //Método cria um hash, baseando no hash anterior, no timestamp, no 'nonce' e no dado
    //Verificar o hasH por OpenSSl

    
    public void mineBlock( int difficulty ) {
        /*Prova de trabalho tentando diferentes valores de variáveis ​​no bloco até que seu hash comece 
        com um certo número de 0s.*/
        //Cria um array com tamanho da dificuldade 
        String target = new String( new char[difficulty] ).replace('\0', '0');
        
            while ( !hash.substring(0, difficulty).equals(target) ) { // Divide o hash da posicao 0, ate a qta 0
                this.nonce++;
                this.hash = calculateHash(); // o nonce serve para a quantdade de hash gerados...
            } // Gera varios hash's, ate que algum contenha a qtde desejadas de 0 no inicio

            
            JOptionPane.showMessageDialog(null, "Block Mined!!!: " + this.hash);
            JOptionPane.showMessageDialog(null, "Print in Mine " + Block.printBlock(this) );
    }
    
    private String calculateHash() {
        String calculatedhash = 
            StringUtil.applySha256( previousHash + Long.toString(timeStamp)
                + Integer.toString(nonce)+ this.dados);
    
        return calculatedhash;
    }
    
    public static String printBlock(Block block){
        
        if( block != null ){
            
            if( block.getHash().equals("incomplent block ") )
                block.calculate_hash();
            
            String dados = "Hash do Bloco: " + block.getHash() + "\nHash do Bloco anterior: " + block.getPreviousHash() + 
                    "\nLista de Transações:\n" + transactionList(block);
            
            return dados;
        }
        
        return "Invalid Block";
    }
    
    public static String transactionList(Block block){
        String nomes = "";
        for(File each : block.dados){
            nomes += each.getName() + "\n";
        }
        return nomes;
    }
    
    public int getNonce(){
        return this.nonce;
    }

    public String getHash() {
        return this.hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getQtdeTransacoes() {
        return amount_transactions;
    }
    
    
    
    
}
