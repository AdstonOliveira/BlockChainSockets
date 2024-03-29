package Client.options;
/**
 * @author Suporte-04
 */
public class DadosSecao {

    private int zona;
    private int municipio;
    private int secao;
    private int aptos_votar;
    private int comparecimentos;
    private int faltosos;
    private int habilitados;
    private int local;

    @Override
    public String toString() {
        return "Dados da Secao{" 
                + "\nzona = " + zona + ", municipio = " + municipio + ", secao = " + secao 
                + "\naptos_votar = " + aptos_votar + ", comparecimentos = " + comparecimentos 
                + "\nfaltosos = " + faltosos + ", habilitados = " + habilitados + ", local = "+ local +"\n}";
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }
    

    
    
    
    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public int getSecao() {
        return secao;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }

    public int getAptos_votar() {
        return aptos_votar;
    }

    public void setAptos_votar(int aptos_votar) {
        this.aptos_votar = aptos_votar;
    }

    public int getComparecimentos() {
        return comparecimentos;
    }

    public void setComparecimentos(int comparecimentos) {
        this.comparecimentos = comparecimentos;
    }

    public int getFaltosos() {
        return faltosos;
    }

    public void setFaltosos(int faltosos) {
        this.faltosos = faltosos;
    }

    public int getHabilitados() {
        return habilitados;
    }

    public void setHabilitados(int habilitados) {
        this.habilitados = habilitados;
    }
    


    
}
