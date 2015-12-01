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
}
