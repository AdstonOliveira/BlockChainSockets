package Client.options;

import java.sql.Time;
import java.util.Date;

/**
 * @author Suporte-04
 */
public class DadosUrna{

    private String id_urna;
    private Date dt_abertura, dt_fechamento;

    public String getId_urna() {
        return id_urna;
    }

    public void setId_urna(String id_urna) {
        this.id_urna = id_urna;
    }

    public Date getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(Date dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public Date getDt_fechamento() {
        return dt_fechamento;
    }

    public void setDt_fechamento(Date dt_fechamento) {
        this.dt_fechamento = dt_fechamento;
    }

}
