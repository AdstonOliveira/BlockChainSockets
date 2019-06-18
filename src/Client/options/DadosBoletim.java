package Client.options;
/**
 * @author Suporte-04
 */
public class DadosBoletim {

    private int num_resumo;
    
    private TipoEleicao tipo_eleicao;
    private DadosSecao dados_secao;
    private DadosUrna dados_urna;
    
    private Resultados resultados;

    public DadosBoletim(TipoEleicao tipo_eleicao, DadosSecao dados_secao, DadosUrna dados_urna, Resultados resultados) {
        this.tipo_eleicao = tipo_eleicao;
        this.dados_secao = dados_secao;
        this.dados_urna = dados_urna;
        this.resultados = resultados;
    }
    public DadosBoletim(){
        this.tipo_eleicao = new TipoEleicao();
    }
    public DadosBoletim(TipoEleicao tipo_eleicao){
        this.tipo_eleicao = tipo_eleicao;
    }
    
    

    public int getNum_resumo() {
        return num_resumo;
    }

    public void setNum_resumo(int num_resumo) {
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

    public Resultados getResultados() {
        return resultados;
    }

    public void setResultados(Resultados resultados) {
        this.resultados = resultados;
    }
    
    
    

    
}
