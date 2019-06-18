package Client.options;
import java.util.ArrayList;
/**
 * @author Suporte-04
 */
public class DadosBoletim {

    private String num_resumo;
    
    private TipoEleicao tipo_eleicao;
    private DadosSecao dados_secao;
    private DadosUrna dados_urna;
    private ArrayList<Resultado> resultados;

    public DadosBoletim(){
        this.tipo_eleicao = new TipoEleicao();
        this.dados_secao = new DadosSecao();
        this.dados_urna = new DadosUrna();
    }
    
    public DadosBoletim(TipoEleicao tipo_eleicao){
        this.tipo_eleicao = tipo_eleicao;
    }
    public String getNum_resumo() {
        return num_resumo;
    }

    public void setNum_resumo(String num_resumo) {
        this.num_resumo = num_resumo;
    }

    public TipoEleicao getTipo_eleicao() {
        return tipo_eleicao;
    }

    public void setTipo_eleicao(TipoEleicao tipo_eleicao) {
        this.tipo_eleicao = tipo_eleicao;
    }

    public DadosSecao getDados_secao() {
        return dados_secao;
    }

    public void setDados_secao(DadosSecao dados_secao) {
        this.dados_secao = dados_secao;
    }

    public DadosUrna getDados_urna() {
        return dados_urna;
    }

    public void setDados_urna(DadosUrna dados_urna) {
        this.dados_urna = dados_urna;
    }

    public ArrayList<Resultado> getResultados() {
        return resultados;
    }

    public Resultado getResultado(String vaga){
        for( Resultado each : resultados ){
            if(each.getVaga().equalsIgnoreCase(vaga))
                return each;
        }
        return null;
    }
    
    public void setResultados(ArrayList<Resultado> resultados) {
        this.resultados = resultados;
    }
    
    
    

    
}
