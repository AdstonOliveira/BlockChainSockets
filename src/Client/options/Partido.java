package Client.options;

import java.util.ArrayList;

/**
 * @author Suporte-04
 */
public class Partido {

    private int numero;
    private int votos_legenda = 0;
    private ArrayList<Candidato> candidatos = new ArrayList();
    private String cod_verificador;

    public Partido(int numero, String cod_verificador) {
        this.numero = numero;
        this.cod_verificador = cod_verificador;
    }
    
    public void printPartido(){
        System.out.println("_____________________");
        System.out.println("Partido Num: " + this.numero);
        System.out.println("Cod Identificador: " + this.getCod_verificador());
        System.out.println("_____________________");
        System.out.println("Candidatos: ");
        for(Candidato each : candidatos){
            each.imprime_candidato();
        }
        System.out.println("_____________________");
        System.out.println("Votos direto na legenda: " + this.getVotos_legenda());
        System.out.println("Total Votos partido: " + this.getVotos());
        System.out.println("_____________________");
    }
    
    
    
    public int getVotos(){
        int total_legenda = 0;
        for(Candidato each : candidatos){
            total_legenda += each.getVotos();
        }
        
        return total_legenda + votos_legenda;
    }

    public int getVotos_legenda() {
        return votos_legenda;
    }

    public void setVotos_legenda(int votos_legenda) {
        this.votos_legenda = votos_legenda;
    }
    
    




    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public String getCod_verificador() {
        return cod_verificador;
    }

    public void setCod_verificador(String cod_verificador) {
        this.cod_verificador = cod_verificador;
    }
    
    
    
}
