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


/**
 *
 * @author Albert, Arnau i Marc
 */
public class Controlador implements Serializable
{
    /* Elements temporals pel funcionament */
    Client tmpClient;
    public Controlador ()
    {
    }
    
    /**
     * Pregunta si el cliente ha echo una reserva
     * @return Bolean el qual true si la echo i false sino.
     */
    public boolean HaveReserva (){ return tmpClient.HaveReserva (); }
}
