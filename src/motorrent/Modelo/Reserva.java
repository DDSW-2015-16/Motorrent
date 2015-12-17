/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

/**
 *
 * @author Marc
 */
public class Reserva {
    private String id;
    private int Retard;
    private Data DataR;
    private Data DataD;
    private Data DataRealitzacio;
    private String Origen;
    private String Desti;
    private String m;
    private int cost;
    private int falta;
    private String horaR;
    private String horaD;
    private Moto moto;
    
    public Reserva() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setDataR(String DataR) {
        this.DataR = new Data(DataR);
    }

    public void setDataD(String DataD) {
        this.DataD = new Data(DataD);
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public void setDesti(String Desti) {
        this.Desti = Desti;
    }

    public void setM(String m) {
        this.m = m;
    }
    
    public String getM() {
        return m;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setFalta(int falta) {
        this.falta = falta;
    }
    
    public void setHoraR(String horaR) {
        this.horaR = horaR;
    }
    public void setHoraD(String horaD) {
        this.horaD = horaD;
    }
    
    public void setMoto(Moto mo) {
        moto = mo;
    }

    @Override
    public String toString() {
        String s = "";
        s +=  "Reserva:" + "\n" +
                "id: " + id + "\n" +
                "Local Origen: " + Origen +"\n" +
                "Data Recollida =" + DataR + " Hora Recollida = " + horaR + "\n" +
                "Local Desti: " + Desti + "\n" +
                "Data Devolucio =" + DataD + " Hora Recollida = " + horaD + "\n" +
                "Moto: " + m + "\n" +
                "cost: " + cost + "\n" +
                "falta: " + falta + "\n";
        return s;
    }

    boolean comprovarCodi(String codi) {
        if(codi == id) {
            return true;
        }
        else {
            return false;
        }
    }

    public Moto getMoto() {
        return moto;
    }
    
}
    
