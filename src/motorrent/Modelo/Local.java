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
        int i;
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
}
