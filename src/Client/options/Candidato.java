package Client.options;
/**
 * @author Suporte-04
 */
public class Candidato{
    
    private String nome;
    private int numero;
    private String vaga;
    private int votos = 0;
    
    public Candidato( String vaga, int numero, int votos, String nome ){
        this.vaga = vaga;
        this.numero = numero;
        this.votos = votos;
        this.nome = nome;
    }

    public Candidato() {
    }

    
    public void imprime_candidato(){
        System.out.println("Nome: " + this.getNome() + " num: " + this.getNumero());
        System.out.println("Candidato ao cargo: " + this.getVaga());
        System.out.println("Total votos candidato: "+ this.getVotos());
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
    
}
