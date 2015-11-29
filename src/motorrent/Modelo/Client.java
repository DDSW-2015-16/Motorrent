/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Albert, Arnau i Marc
 */
public class Client extends Usuari
{
    private boolean reserva;
    private String Nom;
    private String Cognom;
    private String DNI;
    private int faltes;
    private String Correu;
    private int Tel;
    private boolean Vip;
    private Adreça adr;
    private ComptaCorrent cc;
    private ArrayList<Reserva> historial = new ArrayList<Reserva>();
    private Data dataN;
    private Data dataIns;
    
    public Client ()
    {
        reserva = false;
    }
    
    public void setStatReserva (boolean e) { reserva = e; }
    
    public boolean HaveReserva () {return reserva;}
}
