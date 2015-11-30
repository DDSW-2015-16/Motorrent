/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Controlador;

/* Paquets propis */
import motorrent.Modelo.Client;

/* Paquets de java */
import java.io.Serializable; /* Per poder guardar els canvis */
import java.util.ArrayList;
import motorrent.Modelo.Local;
import motorrent.Modelo.Usuari;


/**
 *
 * @author Albert, Arnau i Marc
 */
public class Motorent implements Serializable
{
    /* Elements temporals pel funcionament */
    Client tmpClient;
    ArrayList lst_usuari;
    ArrayList lst_local;
    
    public Motorent ()
    {
        /* Normalment aquest no es necessari, pero ara no tinc llistes... */
        tmpClient = new Client ();
        lst_usuari = new ArrayList<Usuari>();
        lst_local = new ArrayList<Local>();
        
        MotoRentDataManager d = new MotoRentDataManager();
        d.obtenirDades (this, "../../data/MotoRent.xml");
    }
    
    /**
     * Pregunta si el cliente ha echo una reserva
     * @return Bolean el qual true si la echo i false sino.
     */
    public boolean HaveReserva (){ return tmpClient.HaveReserva (); }
    
    /**
     * Dir que el client a fet una reserva.
     * Un estat de moment necessari per a poder fer els experiments amb el menu
     */
    public void MakeReserva () { tmpClient.setStatReserva(true); }
    
    /**
     * Parser, amb XML
     */
    public void crearLocal(String id, String capacitat, String gestorID, String adreca)
    {
        /* Aqui fer un programa per a controlar el gestorID */
        Local l = new Local (id, CastStringInt (capacitat), adreca);
        lst_local.add(l);
    }
    
    /*
    Canvi de string a int
    ja que mes d'un cop, i fins i tot el vista o pot fer
    */
    private int CastStringInt (String e)
    {
        int s;
  /*vigilar error */
            s = Integer.parseInt(e);
            return s;
    }
}
