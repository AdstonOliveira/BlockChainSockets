package Client.options;

import java.util.ArrayList;

/**
 * @author Suporte-04
 */
public class Resultado {
    
    private String vaga;
    private final ArrayList<Partido> partidos = new ArrayList();

    public Resultado(String vaga) {
        this.vaga = vaga;
    }
    
    public int totalPartidos(){
        return partidos.size();
    }


    public void addPartido(Partido partido){
        this.partidos.add(partido);
    }
    
    public Partido getPartido(int num){
        for(Partido each : partidos){
            if(each.getNumero() == num){
               return each;
            }
        }
        return null;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }





}
