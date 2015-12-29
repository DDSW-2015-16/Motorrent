/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param id id de la reserva
     * @param client id del client que te la reserva
     * @param moto id moto escollida
     * @param cost cost reserva
     * @param falta faltes que ha acumulat el client
     * @param local_inici local on ha recollit la moto
     * @param hora_inici hora inici del prestec
     * @param fecha_inici dia inici prestec
     * @param local_fi local on ha retornar la moto
     * @param hora_fi hora fi prectec
     * @param fecha_fi data fi prectes
 * @author Leiva
 */
public class Reserva {
    private String id;
    private String idC;
    private Data DataR;
    private Data DataD; 
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

    public Data getDataR() {
        return DataR;
    }

    public int getCost() {
        return cost;
    }

    public int getMesDataR() {
        
        return DataR.getMes() ;
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
    public void addFalta() {
        ++falta;
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

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
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
    /**
     * Metode que comprova si un codi es correcte
     * @param codi codi de la moto
     * @return true o false segons si el codi es correcte o no
     */
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
    /**
     * Metode que calcula el cost d'una reserva
     */
    public void calcularCost() {
        cost = 0;
        int a = 0;
        try {
            String r = DataR.toString()+ " " + horaR;
            String d = DataD.toString()+ " " + horaD;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dateR = simpleDateFormat.parse(r);
            Date dateD = simpleDateFormat.parse(d);
            a = (int) ((dateD.getTime() - dateR.getTime())/(1000*3600));
            
            
        } catch (ParseException ex) {
            Logger.getLogger(Reserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(a > 24) {
            cost += 15*(a/(24));
            a -= (((int)(a/24))*24);
        }
        if(a%3600 == 0) {
            cost += 1;
        }
        if(a%3600 > 0) {
            cost += 1;
        }
        
    }

    
}
    
