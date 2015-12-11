/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Marc
 */
public class Local {
    private String id;
    private int capacitat;
    private ArrayList<Moto> lst_moto = new ArrayList<Moto>();
    private ArrayList<Reserva> lst_reserva = new ArrayList<Reserva>();
    private Adreça adreça;
    
    public Local (String eid, int ecapacitat, String adreca)
    {
        id = eid;
        capacitat = ecapacitat;
        adreça = new Adreça (adreca);
    }
    
    /******************* PARSER **************************/
    /**
     * Creo aqui la moto pel ... algo estrany de teoria
     * @param id
     * @param matricula
     * @param marca
     * @param model
     * @param color
     * @param estat 
     */
    public void crearMoto(String id, String matricula, String marca, String model, String color, String estat)
    {
        Moto m = new Moto (id, matricula, marca, model, color, estat);
        lst_moto.add(m);
    }
    
    @Override
    public String toString () {
        String s = "";
        s = s + 
                "ID: " + id + "\n" +
                "Capacitat: " + capacitat + "\n" + 
                "Adreça: " + adreça + "\n";
        return s;
    }
    public String imprimirMotos() {
        String s = "";
        int i;
        s += "\nMotos del local: \n";
        for(i = 0; i < lst_moto.size(); ++i) {
            s += i + ".- \n";
            s+= lst_moto.get(i) + "\n";
        }
        return s;
    }
    
    public String getId() {
        return id;
    }
    
    public int getNumMotos() {
        return lst_moto.size();
    }
    
    public int getCapacitat() {
        return capacitat;
    }
    
    public void addReserva(String idLO, String idM, String idD, String dR, String hR, String dD, String hD, String id) {
        Reserva res = new Reserva();
        res.setOrigen(idLO);
        res.setM(idM);
        res.setDataD(dD);
        res.setDataR(dR);
        res.setDesti(idD);
        res.setHoraD(hD);
        res.setHoraR(hR);
        res.setId(id);
        lst_reserva.add(res);
    }
    
    public Moto SeleccionarMoto(String id) {
        Moto tmp = null;
        for(int i = 0; i < lst_moto.size(); ++i) {
            if (((Moto)lst_moto.get(i)).getId().equals(id)) {
                ((Moto)lst_moto.get(i)).setNoDisponible();
                tmp = (Moto)lst_moto.get(i);
            }
        }
        return tmp;
    }
}
