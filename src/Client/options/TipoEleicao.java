package Client.options;
/**
 * @author Suporte-04
 */
public class TipoEleicao {
    
    private int id;
    private String descricao;
    private int ano;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
       this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    




}


