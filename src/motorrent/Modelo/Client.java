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
    private String Vip;
    private Adreça adr;
    private ComptaCorrent cc;
    private ArrayList<Reserva> historial = new ArrayList<Reserva>();
    private Data dataN;
    private Data dataIns;
    private String id;
    private String renovacio;
    private Reserva res;
    public Client() {
        
    }
    
    public Client (String id, String nom, String dni, String adreca, String usuari, String password, String vip, String renovacio, int faltes)
    {
        super(usuari,password);
        this.id = id;
        Nom = nom;
        DNI = dni;
        adr = new Adreça(adreca);
        Vip = vip;
        this.renovacio = renovacio;
        this.faltes = faltes;
        
    }
    public String toString() {
        String s = "";
        s += "Client: \n" +
                "Identificador: " + id + "\n" +
                "Nom: " + Nom + "\n" +
                "DNI: " + DNI + "\n" +
                "Adreça: " + adr + "\n" +
                "Faltes: " + faltes +"\n" +
                "VIP :" + Vip + "\n";
        
        return s;
    }
    /*Afegir al diagrama de classes*/
    public boolean hasReserva(){
        if (res!= null){
            return false;
        }else{
            return true;
        }
    }
    
    public String getUser() {
        return super.getUsuari();
    }
    public String getPass() {
        return super.getPassword();
    }

    public void createReserva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
