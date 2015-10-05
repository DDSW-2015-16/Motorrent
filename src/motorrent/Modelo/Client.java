/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

/**
 *
 * @author Albert, Arnau i Marc
 */
public class Client
{
    private boolean reserva;
    
    public Client ()
    {
        reserva = false;
    }
    
    public boolean HaveReserva () {return reserva;}
}
