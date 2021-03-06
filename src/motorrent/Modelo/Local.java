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
    public void crearMoto(String id, String matricula, Object especificacioMoto, String color, String estat)
    {
        Moto m = new Moto (id, matricula, especificacioMoto, color, estat);
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
    /**
     * Metode que formata la sortida per imprimir motos
     * @return  string amb totes les motos
     */
    public String imprimirMotos() {
        String s = "";
        int i;
        s += "\nMotos del local: \n" +
                "--------------- \n";
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
    /**
     * Metode que afegeix una reserva al local seleccionat
     * @param res reserva que sera afegida
     */
    public void addReserva(Reserva res) {
        lst_reserva.add(res);
    }
    /**
     * Metode que retorna una moto a partir del seu id
     * @param id id de la moto
     * @return objecte moto
     */
    public Moto SeleccionarMoto(String id) {
        Moto tmp = null;
        for(int i = 0; i < lst_moto.size(); ++i) {
            if (((Moto)lst_moto.get(i)).getId().equals(id)) {
                tmp = (Moto)lst_moto.get(i);
            }
        }
        return tmp;
    }
    /**
     * Metode que comprova que un codi existeix en el sistema
     * @param codi codi de la reserva 
     * @return true o false segons si existeix la reserva al sistema
     */
    public Reserva comprovarCodi(String codi) {
        Reserva res = null;
        Boolean trobat = false;
        for (int i = 0; i < lst_reserva.size() && !trobat; ++i){
            if(((lst_reserva.get(i)).getId()).equals(codi)) {
                res = lst_reserva.get(i);
                trobat = true;
            }
        }
        return res;
    }
    public void addMoto(Moto m) {
        lst_moto.add(m);
    }
    public void removeMoto (Moto m)
    {
        lst_moto.remove(m);
    }
    
    
}
