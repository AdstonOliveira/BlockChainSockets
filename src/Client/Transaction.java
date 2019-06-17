package Client;
import Client.options.BoletimUrna;
/**
 * @author Suporte-04
 */
public class Transaction {

    private int id;
    private String remetente;
    private BoletimUrna boletim;
    private String hash;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public BoletimUrna getBoletim() {
        return boletim;
    }

    public void setBoletim(BoletimUrna boletim) {
        this.boletim = boletim;
    }
    


    
    




    
}
