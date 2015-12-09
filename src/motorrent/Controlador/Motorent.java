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
import motorrent.Modelo.Administrador;
import motorrent.Modelo.Gerent;



/**
 *
 * @author Albert, Arnau i Marc
 */
public class Motorent implements Serializable
{
    /* Elements temporals pel funcionament */
    ArrayList lst_usuari;
    ArrayList lst_local;
    Local tmpL; /* El faig anar per a crear, perque en aquest afeguira les motos */
    Usuari tmpU;
    
    public Motorent (String xml)
    {
        /* Normalment aquest no es necessari, pero ara no tinc llistes... */
        lst_usuari = new ArrayList<Usuari> ();
        lst_local = new ArrayList<Local> ();
        
        MotoRentDataManager d = new MotoRentDataManager();
        d.obtenirDades (this, xml);
    }
    
    /**
     * Pregunta si el cliente ha echo una reserva
     * @return Bolean el qual true si la echo i false sino.
     */
    public boolean HaveReserva (){ return false; }
    
    /**
     * Dir que el client a fet una reserva.
     * Un estat de moment necessari per a poder fer els experiments amb el menu
     *
    
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
    
    public void crearClient(String id, String nom, String dni, String adreca, String usuari, String password, String vip, String renovacio, String faltes) {
        Client tmpC = new Client(id,nom,dni,adreca,usuari,password,vip,renovacio,ParseInt (faltes));
        lst_usuari.add(tmpC);
        
    }
    public void crearGerent(String id, String nom, String usuari, String password) {
        Gerent tmpG = new Gerent(usuari,password,id,nom);
        lst_usuari.add(tmpG);
        
    }
    public void crearAdministrador(String id, String nom, String usuari, String password) {
        Administrador tmpA = new Administrador(usuari,password,id,nom);
        lst_usuari.add(tmpA);
        
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
    /************************** Imprimir *****************/
    /**
     * 
     */
    public String ImprimirLocals() {
        String sortida = "";
        int i;
        for (i = 0; i < lst_local.size(); ++i) {
            sortida += "Local: " + i + ".- \n";
            sortida += (lst_local.get(i)) + "\n";
        }
        return sortida;
    }
    
    public String ImprimirUsuaris() {
        String sortida = "";
        int i;
        for(i = 0; i < lst_usuari.size(); ++i) {
            sortida += i + ".- \n";
            sortida += (lst_usuari.get(i)) + "\n";
        }
        return sortida;
    }
    
    /**
     * LogIn
     */
    public boolean checkUser(String us, String ps) {
        boolean trobat = false;
        for(Object u: lst_usuari) {
            if((((Usuari)u).getUsuari().equals(us)) && ((Usuari)u).getPassword().equals(ps)){
                trobat = true;
                tmpU = (Usuari) u;
            }       
        }
        return trobat;
    }
    public String typeUser() {
        String s = "";
        if(tmpU instanceof Client) {
            s = "c";
        }
        else if(tmpU instanceof Gerent) {
            s = "g";
        }
        else if(tmpU instanceof Administrador) {
            s = "a";
        }
        return s;
    }
    /**
     * ExisteixUser
     */
    public boolean existeixUsuari(String usuari) {
        boolean existeix = false;
        int i;
        for(i = 0; i < lst_usuari.size() && !existeix; ++i) {
            if(((Usuari)lst_usuari.get(i)).getUsuari().equals(usuari)) {
                existeix = true;
            }
        }
        return existeix;
    }
    
    public void registreClient(String u, String p, String n, String d, String a) {
        String id = "c";
        int i = 1;
        for(Object us: lst_usuari) {
            if(us instanceof Client) {
                ++i;
            }
        }
        id += i;
        Client nou = new Client(id,n,d,a,u,p,"false","fasle",0);
        lst_usuari.add(nou);
    }
}

