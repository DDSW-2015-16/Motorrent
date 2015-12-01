/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Controlador;

/* Paquets propis */
import motorrent.Modelo.Client;
import motorrent.Modelo.Local;
import motorrent.Modelo.Usuari;

/* Paquets de java */
import java.io.Serializable; /* Per poder guardar els canvis */
import java.util.ArrayList;
import java.lang.NumberFormatException;



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
    Local tmpL; /* El faig anar per a crear, perque en aquest afeguira les motos */
    
    public Motorent (String xml)
    {
        /* Normalment aquest no es necessari, pero ara no tinc llistes... */
        tmpClient = new Client ();
        lst_usuari = new ArrayList<Usuari> ();
        lst_local = new ArrayList<Local> ();
        
        MotoRentDataManager d = new MotoRentDataManager();
        d.obtenirDades (this, xml);
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
    
    /*******************    Parse   *******************************************************/
    /**
     * Parser, amb XML
     */
    public void crearLocal(String id, String capacitat, String gestorID, String adreca)
    {
        /* Aqui fer un programa per a controlar el gestorID */
        tmpL = new Local (id, ParseInt (capacitat), adreca);
        lst_local.add(tmpL);
    }
    
    public void crearMoto(String id, String matricula, String marca, String model, String color, String estat)
    {
        tmpL.crearMoto (id, matricula, marca, model, color, estat);
    }
    
    /*
     * Cambia l'estring en digit.
     * en cas de no funcionar, retornara un zero
     */
    private int ParseInt (String e)
    {
        int s;
        try
        {
            s = Integer.parseInt(e);
        } catch (NumberFormatException ex)
        {   s = 0;  }
        return s;
    }
}
