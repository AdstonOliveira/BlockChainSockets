package Client.options;
import java.io.File;
/**
 * @author Suporte-04
 */
public class BoletimUrna {
    //Conteudo completo
    private File xml;
    private String hash_file;
    
    private final DadosBoletim dados;
    
    public BoletimUrna(){
        this.dados = new DadosBoletim();
    }
    
    
    public DadosBoletim getDados(){
        return this.dados;
    }

    public File getXml() {
        return xml;
    }

    public void setXml(File xml) {
        this.xml = xml;
    }

    public String getHash_file() {
        return hash_file;
    }

    public void setHash_file(String hash_file) {
        this.hash_file = hash_file;
    }
    
    
    
    

    
}
