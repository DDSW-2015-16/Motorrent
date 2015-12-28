/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Controlador;

import motorrent.Modelo.Client;
import motorrent.Modelo.Local;
import motorrent.Modelo.Usuari;
import motorrent.Modelo.Administrador;
import motorrent.Modelo.Gerent;
import motorrent.Modelo.EspecificacioMoto;
import motorrent.Modelo.Moto;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.NumberFormatException;
import motorrent.Modelo.Reserva;
import motorrent.Vista.Vista;




/**
 *
 * @author Albert, Arnau i Marc
 */
public class Motorent implements Serializable
{
    /* Elements temporals pel funcionament */
    ArrayList lst_usuari;
    ArrayList lst_local;
    ArrayList lst_especificacio_moto;
    Local tmpL; /* El faig anar per a crear, perque en aquest afeguira les motos */
    Usuari Usuari;
    int numeroReserves = 1;
    Vista vista;
    
    public Motorent (String xml, Vista vs)
    {
        vista = vs;
        /* Normalment aquest no es necessari, pero ara no tinc llistes... */
        lst_usuari = new ArrayList<Usuari> ();
        lst_local = new ArrayList<Local> ();
        lst_especificacio_moto = new ArrayList<EspecificacioMoto> ();
        
        MotoRentDataManager d = new MotoRentDataManager();
        d.obtenirDades (this, xml);
    }
    
    
    /*******************   PARSER   *******************************************************/
    /**
     * Parser, amb XML
     */
    public void crearLocal(String id, String capacitat, String gestorID, String adreca)
    {
        
        /* Aqui fer un programa per a controlar el gestorID */
        tmpL = new Local (id, ParseInt (capacitat), adreca);
        for(int i = 0; i < lst_usuari.size(); ++i) {
            if(lst_usuari.get(i) instanceof Gerent) {
                if(((Gerent)lst_usuari.get(i)).getID().equals(gestorID)) {
                    ((Gerent)lst_usuari.get(i)).setLocal(tmpL);
                }
            }
        }
        lst_local.add(tmpL);
    }
    
    public void crearMoto(String id, String matricula, String marca, String model, String color, String estat)
    {
        int i;
        EspecificacioMoto em = null;
        for (i = 0; (i < lst_especificacio_moto.size()) && (em == null); i++)
            if (((EspecificacioMoto) lst_especificacio_moto.get(i)).esIgual (marca, model))
                em = (EspecificacioMoto) lst_especificacio_moto.get(i);
        if ( em == null )
            em = new EspecificacioMoto (marca, model);
        tmpL.crearMoto (id, matricula, em, color, estat);
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
                    numeroReserves++;
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
    
    
    
    /************************** IMPRIMIR *****************/
    /**
     * 
     */
    public String imprimirLocal() {
        String sortida = "";
        int i;
        for (i = 0; i < lst_local.size(); ++i) {
            sortida += "Local: " + (i+1) + ".- \n" +
                "----------------- \n";
            sortida += (lst_local.get(i)) + "\n";
        }
        return sortida;
    }

    public void ImprimirLocalsMotos() {
        String sortida = "";
        int i;
        for (i = 0; i < lst_local.size(); ++i) {
            sortida += "Local: " + (i+1) + ".- \n"+
                "----------------- \n";
            sortida += (lst_local.get(i)) + "\n";
            sortida += ((Local) lst_local.get(i)).imprimirMotos() + "\n";
        }
        vista.escriu(sortida);
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
    
    /****** LOGIN *****/
   public void login() {
        vista.escriu ("Introdueixi el seu nom d'usuari: ");
        String us = vista.llegeixString();
        vista.escriu("Introdueixi la seva contrasenya:");
        String ps = vista.llegeixString();
        boolean existeix = false;
        for(Object u: lst_usuari) {
            if(((Usuari)u).checkUser(us, ps)) {
                existeix = true;
                Usuari = (Usuari) u;
            }
        }       
        if(existeix) {
            if(Usuari instanceof Client) {
                int max = 3;
                if(((Client)Usuari).getFaltes() >= max) {
                    vista.escriu("Té mes de 3 faltes:");
                }
                else {
                    vista.MenuCliente();
                }
            }
            else if(Usuari instanceof Gerent) {
                vista.MenuGerente();
            }
            else if(Usuari instanceof Administrador) {
                vista.MenuAdministrador();
            }
        }
        else {
            vista.escriu("Usari o contrasenya erronies.");
        }
        
    }
    
    /**** REGISTRE CLIENT ****/
    public void Registre() {
        vista.escriu("Introdueixi el nom d'usuari que desitji :");
        String us = vista.llegeixString();
        boolean existeix = true;
        while(existeix) {
            for(Object u: lst_usuari) {
                if(((Usuari)u).existeix_usuari(us)) {
                    vista.escriu("L'usuari ja existeix.");
                    vista.escriu("Introdueixi el nom d'usuari que desitji :");
                    us = vista.llegeixString();
                }
                else {
                    existeix = false;
                }
            }
        }
        
        if(!existeix) {
            Client u = new Client();
            u.setUser(us);
            demanarDades(u);
        }
    }
    
    
    public void demanarDades(Usuari u) {
        String id = "c";
        int i = 1;
        for(Object us: lst_usuari) {
            if(us instanceof Client) {
                ++i;
            }
        }
        id += i;
        Client c = (Client) u;
        c.setId(id);
        vista.escriu("Introdueix la contrasenya: ");
        c.setPassword(vista.llegeixString());
        vista.escriu("Introduciex el nom i cognoms: ");
        c.setNom(vista.llegeixString());
        vista.escriu("Introdueix el seu DNI: ");
        c.setDNI(vista.llegeixString());
        vista.escriu("Introdueix la seva adreça ");
        c.setAdr(vista.llegeixString());
        c.setVip("false");
        lst_usuari.add(c); 
 }

    
    
    /**
     * RESERVA
     */
    
    public void ferReserva() {
        if(((Client)Usuari).hasReserva()) {
            vista.escriu("Voste no pot fer una reserva ja que en té una d'activa.");
        }
        else {
            Reserva res = ((Client)Usuari).crearReserva();
            res.setId("r"+(numeroReserves++));
            Local or = seleccionarLocal();
            res.setOrigen(or.getId());
            Moto m = seleccionarMoto(or);
            while(!m.getEstat().equals("disponible")) {
                vista.escriu("La moto seleccionada no està disponible.");
                vista.escriu("Seleccioni una altra moto:");
                m = seleccionarMoto(or);
            }
            res.setMoto(m);
            res.setM(m.getId());
            Local d = seleccionarLocal();
            while (!checkCapacitat(d)) {
                vista.escriu("El local no té suficient espai");
                vista.escriu("Seleccioni un altre local");
                d = seleccionarLocal();
            }
            m.setNoDisponible();
            d.addMoto(m);
            res.setDesti(d.getId());
            vista.escriu("Introdueixi la data de recollida(DD/MM/AAAA): ");
            res.setDataR(vista.llegeixString());
            vista.escriu("Introdueixi l'hora de recollida(HH:MM:SS): ");
            res.setHoraR(vista.llegeixString());
            vista.escriu("Introdueixi la data de devolució(DD/MM/AAAA): ");
            res.setDataD(vista.llegeixString());
            vista.escriu("Introdueixi l'hora de devolució(HH:MM:SS): ");
            res.setHoraD(vista.llegeixString());
            res.setIdC(((Client)Usuari).getId());
            res.calcularCost();
            ((Client)Usuari).setReserva(res);
            or.addReserva(res);
            d.addReserva(res);
            vista.escriu("Resum de la reserva:");
            vista.escriu(res.toString());
        }
    }  
    
   
    
    /** Retorna null si la entrada es incorrecta */
    private Local seleccionarLocal()
    {
        Local local;
        String i;
        
        local = null;
        
        vista.escriu(imprimirLocal());
        vista.escriu("Seleccioni l'identificador(ID) del local que desitja:");
        i = vista.llegeixString();
        for(Object l:lst_local) {
            if(((Local)l).getId().equals(i)) {
                local = (Local)l;
            }
        }
        return local;
    }
 
    private Moto seleccionarMoto (Local local)
    {
        Moto moto;
        String i;
        
        vista.escriu (local.imprimirMotos());
        vista.escriu("Seleccioni l'identificador(ID) de la moto que desitja:");
        i = vista.llegeixString();
        
        moto = local.SeleccionarMoto(i);
        return moto;
    }
    
    /***** COMPOROVAR CAPACITAT ****/
    /***** AFEGIR AL DIAGRAMA DE CLASSES ****/
    public boolean checkCapacitat(Local l) {
        boolean c = true;
        if(l.getCapacitat() < (l.getNumMotos()+1)) {
            c = false;
        }
        return c;
    }


    /**** GENERAR INFORME ***/
    public void generarInforme(){
        
    }
    /***** ENTREGAR MOTO ****/
    public void entregarMoto() {
        vista.escriu("Introdueixi el codi de la reserva:");
        String c = vista.llegeixString();
        Local o = ((Gerent)Usuari).getLocal();
        Reserva res = o.comprovarCodi(c);
        while(res == null) {
            vista.escriu("El codi de la reserva és incorrecte.");
            vista.escriu("Introdueixi el codi un altre cop: ");
            c = vista.llegeixString();
            res = o.comprovarCodi(c);
        }
        vista.escriu(res.toString());
        Moto m = res.getMoto();
        o.removeMoto(m);
    }
    
    public void RetornarMoto() {
        vista.escriu("Introdueixi el codi de la reserva:");
        String c = vista.llegeixString();
        Local o = ((Gerent)Usuari).getLocal();
        Reserva res = o.comprovarCodi(c);
        int faltes = 0;
        while(res == null) {
            vista.escriu("El codi de la reserva és incorrecte.");
            vista.escriu("Introdueixi el codi un altre cop: ");
            c = vista.llegeixString();
            res = o.comprovarCodi(c);
        }
        vista.escriu(res.toString());
        Client cl = getClient(res.getIdC());
        Moto m = res.getMoto();
        vista.escriu("Introdueixi l'estat de la moto(avariada/dispnible)");
        String es = vista.llegeixString();
        o.removeMoto(m);
        m.setEstat(es);
        o.addMoto(m);
        if(es.equals("avariada")) {
            res.addFalta();
            cl.addFalta();
        }
        vista.escriu("L'ha tornat amb retard(S/N)?");
        if(vista.llegeixString().equals("S")) {
            vista.escriu("Introdueixi data de la devolució(DD/MM/AAAA): ");
            res.setDataD(vista.llegeixString());
            vista.escriu("Introdueixi hora de devolució(HH:MM:SS):");
            res.setHoraD(vista.llegeixString());
            res.calcularCost();
            res.addFalta();
            cl.addFalta();
        }
        cl.addReserva(res);
        cl.rmReservaActiva();
        
    }
    public Client getClient(String id) {
        Client tmp = null;
        for(Object u:lst_usuari) {
            if(u instanceof Client) {
                if(((Client)u).getId().equals(id)) {
                    tmp = (Client) u;
                }
            }
        }
        return tmp;
    }
    
    public void gestionarLocal()
    {
        String s;
        Local rebreMoto;
        int n, idLocal, numLocalO;
        boolean capacitat, trobat;
        Moto motoT;
        
        trobat = false;
        
        rebreMoto = seleccionarLocal ();
        if (rebreMoto == null)
        {
            vista.escriu ( "No s'ha seleccionat el local correctament\n" );
        } else
        {
            motoT = seleccionarMoto(rebreMoto);
            if (!motoT.getEstat().equals("disponible"))
            {
                vista.escriu ( "La moto seleccionada no està disponible\n" );
            } else
            {
                rebreMoto.removeMoto (motoT);
                ((Gerent)Usuari).getLocal().addMoto(motoT);
            }
        }
    }
}

