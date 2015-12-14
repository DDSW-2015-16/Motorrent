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
import motorrent.Modelo.Moto;




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
    Usuari Usuari;
    Moto motoR;
    int numR = 1;

    
    public Motorent (String xml)
    {
        /* Normalment aquest no es necessari, pero ara no tinc llistes... */
        lst_usuari = new ArrayList<Usuari> ();
        lst_local = new ArrayList<Local> ();
        
        MotoRentDataManager d = new MotoRentDataManager();
        d.obtenirDades (this, xml);
    }
    
     
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
    public void crearReserva(String id,String client,String moto, String cost, String falta, String local_inici,String hora_inici,String fecha_inici, String local_fi,String hora_fi,String fecha_fi) {
        for(int i = 0; i < lst_usuari.size(); ++i) {
            if(lst_usuari.get(i) instanceof Client) {
                if (((Client)lst_usuari.get(i)).getId().equals(client)) {
                    ((Client)lst_usuari.get(i)).addReservaHistorial(id, moto, ParseInt(cost), ParseInt(falta), local_inici, hora_inici, fecha_inici, local_fi, hora_fi, fecha_fi);
                    numR++;
                }
            }
        }
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
    public String ImprimirMotos() {
        String i = "";
        i += tmpL.imprimirMotos();
        return i;
    }
    public String ImprimirLocalsMotos() {
        String sortida = "";
        int i;
        for (i = 0; i < lst_local.size(); ++i) {
            sortida += "Local: " + i + ".- \n";
            sortida += (lst_local.get(i)) + "\n";
            sortida += ((Local) lst_local.get(i)).imprimirMotos() + "\n";
        }
        return sortida;
    }
    
    public String ImprimirReservaClient() {
        String s = "Resum de la reserva: \n";
        s += ((Client)Usuari).imprimirReservaActiva();
        return s;
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
    public String ImprimirH() {
        String h = "";
        h += ((Client)Usuari).imprimirHistorial();
        return h;
    }
    
    /**
     * LogIn
     */
    public boolean checkUser(String us, String ps) {
        boolean trobat = false;
        for(Object u: lst_usuari) {
            if((((Usuari)u).getUsuari().equals(us)) && ((Usuari)u).getPassword().equals(ps)){
                trobat = true;
                Usuari = (Usuari) u;
            }       
        }
        return trobat;
    }
    public String typeUser() {
        String s = "";
        if(Usuari instanceof Client) {
            s = "c";
            int max = 3;
            if(((Client)Usuari).getFaltes()== max) {
                s = "e";
            }
        }
        else if(Usuari instanceof Gerent) {
            s = "g";
        }
        else if(Usuari instanceof Administrador) {
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
        Client nou = new Client(id,n,d,a,u,p,"false","false",0);
        lst_usuari.add(nou);
    }
    
    
    /**
     * RESERVA
     */
    /**
     * En el diagrama de sequencia falta añadir que se guarda en lst_reserva del local origen y destino para
     * despues comprovar el codigo cuando se devuelve la moto o se entrega.
     * @param idLO
     * @param idM
     * @param idD
     * @param dR
     * @param hR
     * @param dD
     * @param hD 
     */
    public void ferReserva(String idLO, String idM, String idD, String dR, String hR, String dD, String hD){
        String id = "r" + (numR++);
        ((Client)Usuari).crearReserva(idLO, idM, idD, dR, hR, dD, hD, id);
        SeleccionarLocal(idLO);
        tmpL.addReserva(idLO, idM, idD, dR, hR, dD, hD, id);
        SeleccionarLocal(idD);
        tmpL.addReserva(idLO, idM, idD, dR, hR, dD, hD, id);
    }
    
    public void SeleccionarLocal(String idL) {
        for(int i = 0; i < lst_local.size(); ++i) {
            if (((Local)lst_local.get(i)).getId().equals(idL)) {
                tmpL = (Local)lst_local.get(i);
            }
        }
    }
    
    /**
     * Comprovar si hi pot cabre la moto al local destí.
     * @return 
     */
    public boolean checkCapacitat() {
        boolean c = true;
        if(tmpL.getCapacitat() < (tmpL.getNumMotos()+1)) {
            c = false;
        }
        return c;
    }
    /* AÑADIR AL DIAGRAMA DE CLASES*/
    public void SeleccionarMotoLocal(String idM) {
        motoR = tmpL.SeleccionarMoto(idM); 
    }
    /*AÑADIR AL DIAGRAMA DE CLASES*/
    public boolean hasReserva() {
        boolean res = false;
        if(((Client)Usuari).hasReserva() == false) {
            res = false;
        }
        else {
            res = true;
        }
        return res;
    }

    public void generarInforme(){
        
    }
}

