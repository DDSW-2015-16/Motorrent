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
 * Controlador principal de l'aplicació, implementat amb el patro MVC.
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
    /**
     *  Constructor de la clase Motorent, encarregat de llegir les dades del fitxer XML per tal de fer-les servir.
     * @param xml nom del fitxer
     * @param vs vista del sistema
     */
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
     * Metode que crea locals a partir de les dades del fitxer xml
     * @param id id del local
     * @param capacitat capacitat del local
     * @param gestorID id del usuari(gerent) que el gestiona el local
     * @param adreca adreca on es troba el local
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
        //afegim local a la llista 
        lst_local.add(tmpL);
    }
    /**
     * Metode que crea motos a partir del fitxer xml
     * @param id id de la moto
     * @param matricula matricula de la moto
     * @param marca marca de la moto
     * @param model model de la moto
     * @param color color de la moto
     * @param estat estat de la moto
     */
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
    /**
     * Metode que crea clients a partir del fitxer xml
     * @param id id del usuari
     * @param nom nom del usuari
     * @param dni dni del usuari
     * @param adreca adreca del usuari
     * @param usuari nom de usuari
     * @param password password del usuari
     * @param vip si es o no vip
     * @param renovacio 
     * @param faltes num de faltes que té
     */
    public void crearClient(String id, String nom, String dni, String adreca, String usuari, String password, String vip, String renovacio, String faltes) {
        Client tmpC = new Client(id,nom,dni,adreca,usuari,password,vip,renovacio,ParseInt (faltes));
        lst_usuari.add(tmpC);
        
    }
    /**
     * Metode que crea gerents a partir del fitxer XML
     * @param id id del gerent
     * @param nom nom del gerent
     * @param usuari nom de usuari del gerent
     * @param password password del gerent
     */
    public void crearGerent(String id, String nom, String usuari, String password) {
        Gerent tmpG = new Gerent(usuari,password,id,nom);
        lst_usuari.add(tmpG);
        
    }
    /**
     * Metode que crea admins a partir del fitxer XML
     * @param id id del admin
     * @param nom nom del admin
     * @param usuari nom de usuari del admin
     * @param password password del admin
     */
    public void crearAdministrador(String id, String nom, String usuari, String password) {
        Administrador tmpA = new Administrador(usuari,password,id,nom);
        lst_usuari.add(tmpA);
        
    }
    /**
     * Metode que crea les reserves a partir del fitxer xml
     * @param id id de la reserva
     * @param client id del client que te la reserva
     * @param moto id moto escollida
     * @param cost cost reserva
     * @param falta faltes que ha acumulat el client
     * @param local_inici local on ha recollit la moto
     * @param hora_inici hora inici del prestec
     * @param fecha_inici dia inici prestec
     * @param local_fi local on ha retornar la moto
     * @param hora_fi hora fi prectec
     * @param fecha_fi data fi prectes
     */
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
    
    /**
     * Metode que retorna un int a partir d'un String en cas contrari retornara 0
     * @param e String a convertir en int
     * @return int
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
     * Metode que imprimeix un local
     * @return String amb els locals disponibles a Motorent 
     * 
     */
    public String imprimirLocal() {
        String sortida = "";
        int i;
        //Recorrem tots els locals
        for (i = 0; i < lst_local.size(); ++i) {
            //formatem la sortida
            sortida += "Local: " + (i+1) + ".- \n" +
                "----------------- \n";
            sortida += (lst_local.get(i)) + "\n";
        }
        //retornem string amb totes les dades
        return sortida;
    }
    
    /**
     * Metode que crida a la vista per imprimir tots els locals amb les motos corresponents
     */
    public void ImprimirLocalsMotos() {
        String sortida = "";
        int i;
        //recorrem tots els locals
        for (i = 0; i < lst_local.size(); ++i) {
            sortida += "Local: " + (i+1) + ".- \n"+
                "----------------- \n";
            //afegim el local
            sortida += (lst_local.get(i)) + "\n";
            //afegim les motos
            sortida += ((Local) lst_local.get(i)).imprimirMotos() + "\n";
        }
        vista.escriu(sortida);
    }
    
    /**
     * Metode que imprimeix tots els usuaris de Motorent
     * @return un String formatat que conte tots els usuaris
     */
    public String ImprimirUsuaris() {
        String sortida = "";
        int i;
        for(i = 0; i < lst_usuari.size(); ++i) {
            sortida += i + ".- \n";
            sortida += (lst_usuari.get(i)) + "\n";
        }
        return sortida;
    }
    
    /****** LOGIN *****/
    /**
     * Metode que gestiona la opcio del login
     */
   public void login() {
       //demanem nom usuari i pass
        vista.escriu ("Introdueixi el seu nom d'usuari: ");
        String us = vista.llegeixString();
        vista.escriu("Introdueixi la seva contrasenya:");
        String ps = vista.llegeixString();
        //comprovem que existeix al sistema
        boolean existeix = false;
        //recorrent tots els usuaris fins trobar el que ens interesa
        for(Object u: lst_usuari) {
            if(((Usuari)u).checkUser(us, ps)) {
                existeix = true;
                Usuari = (Usuari) u;
            }
        }       
        //si existeix
        if(existeix) {
            //comprovem si es un client
            //comprovem que no tingui 3 faltes
            //mostem menu client
            if(Usuari instanceof Client) {
                int max = 3;
                if(((Client)Usuari).getFaltes() >= max) {
                    vista.escriu("Té mes de 3 faltes:");
                }
                else {
                    vista.MenuCliente();
                }
            }
            //si es gerent mostrm menu gerent
            else if(Usuari instanceof Gerent) {
                vista.MenuGerente();
            }
            //si es admin mostrem menu admin
            else if(Usuari instanceof Administrador) {
                vista.MenuAdministrador();
            }
        }
        //si no mostrem error
        else {
            vista.escriu("Usari o contrasenya erronies.");
        }
        
    }
    
    /**** REGISTRE CLIENT ****/
   /**
    * Metode que serveix per registrar nous usuaris
    */
    public void Registre() {
        //demanem nom usuari
        vista.escriu("Introdueixi el nom d'usuari que desitji :");
        String us = vista.llegeixString();
        boolean existeix = true;
        //comprovem que no existeixi ja al sistema
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
        //si no existeix demanem dades i creem usuari
        if(!existeix) {
            Client u = new Client();
            u.setUser(us);
            demanarDades(u);
        }
    }
    
    /**
     * Metode que demana dades a la vista per a que aquesta interaccioni amb l'usuari
     * @param u Usuari on es guardaran les dades
     */
    public void demanarDades(Usuari u) {
        String id = "c";
        int i = 1;
        //assignem id al nou usuari
        for(Object us: lst_usuari) {
            if(us instanceof Client) {
                ++i;
            }
        }
        id += i;
        Client c = (Client) u;
        c.setId(id);
        //demanem totes les dades corresponents
        vista.escriu("Introdueix la contrasenya: ");
        c.setPassword(vista.llegeixString());
        vista.escriu("Introduciex el nom i cognoms: ");
        c.setNom(vista.llegeixString());
        vista.escriu("Introdueix el seu DNI: ");
        c.setDNI(vista.llegeixString());
        vista.escriu("Introdueix la seva adreça ");
        c.setAdr(vista.llegeixString());
        c.setVip("false");
        //afegim l'usuari
        lst_usuari.add(c); 
 }

    
    
    /**
     * RESERVA
     */
    /**
     * Metode que crea reserves, demana les dades corresponents a la vista cuan es necessari
     */
    public void ferReserva() {
        //comprovem que no tingui cap reserva activa
        if(((Client)Usuari).hasReserva()) {
            vista.escriu("Voste no pot fer una reserva ja que en té una d'activa.");
        }
        //si no te cap reserva activa pot fer reserva
        else {
            //li assignem una nova reserva
            Reserva res = ((Client)Usuari).crearReserva();
            res.setId("r"+(numeroReserves++));
            //fem que seleccioni local
            Local or = seleccionarLocal();
            //comprovem que el local no estiogui ple
            while(or == null) {
                vista.escriu("Local no disponible");
                vista.escriu("Seleccioni un altre local:");
                or = seleccionarLocal();
            }
            //fem el mateix per les motos, comprovem que no estigui llogada ni averiada
            res.setOrigen(or.getId());
            Moto m = seleccionarMoto(or);
            while(m == null) {
                vista.escriu("La moto seleccionada no existeix");
                vista.escriu("Seleccioni una altra moto:");
                m = seleccionarMoto(or);
            }
            while(!m.getEstat().equals("disponible")) {
                vista.escriu("La moto seleccionada no està disponible.");
                vista.escriu("Seleccioni una altra moto:");
                m = seleccionarMoto(or);
            }
            res.setMoto(m);
            res.setM(m.getId());
            //fem que seleccioni local
            Local d = seleccionarLocal();
            while(d == null) {
                vista.escriu("Local no disponible");
                vista.escriu("Seleccioni un altre local:");
                d = seleccionarLocal();
            }
            //comprovem que el local no estigui ple
            while (!checkCapacitat(d)) {
                vista.escriu("El local no té suficient espai");
                vista.escriu("Seleccioni un altre local");
                d = seleccionarLocal();
            }
            m.setNoDisponible();
            d.addMoto(m);
            //Fem que introdueixi totes les dades addicionals
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
            //afegim la reserva
            or.addReserva(res);
            d.addReserva(res);
            //fem que imprimeixi totes les dades de la reserva
            vista.escriu("Resum de la reserva:");
            vista.escriu(res.toString());
        }
    }  
    
   
    
   
    /**
     * Metode que comprova que el local que es seleccioni existeix, si no donarpa null
     * @return local, o null depen si ecisteix o no el local
     */
    private Local seleccionarLocal()
    {
        Local local;
        String i;
        
        local = null;
        
        vista.escriu(imprimirLocal());
        vista.escriu("Seleccioni l'identificador(ID) del local que desitja:");
        i = vista.llegeixString();
        //Comprovem que el local existeix
        for(Object l:lst_local) {
            if(((Local)l).getId().equals(i)) {
                local = (Local)l;
            }
        }
        return local;
    }
    /**
     * Metode que selecciona una moto d'un local
     * @param local local on es troba la moto
     * @return  moto
     */
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
    
    /**
     * Metode que comprova la capacitat de un local 
     * @param l local on comprovarem la capacitat
     * @return  true o false segons si té la capacitat suficient
     */
    public boolean checkCapacitat(Local l) {
        boolean c = true;
        if(l.getCapacitat() < (l.getNumMotos()+1)) {
            c = false;
        }
        return c;
    }


    /**** GENERAR INFORME ***/
    /**
     * Metode que genera el informe del mes seleccionat a partir del numero del mes que vulguem
     */
    public void generarInforme(){
        vista.escriu("Escriu el mes (número) del que vol generar l'informe'");
        String informe = "Informe del mes "  ;
        String mes = vista.llegeixString();
        informe +=  mes + "\n";
        //comprovem que siguin clients de tots els usuaris de Motorent
        for (Object u:lst_usuari){
            if(u instanceof Client){
                informe += ((Client)u).getInforme(mes);
            }
        }
        
        vista.escriu(informe);
    }
    /***** ENTREGAR MOTO ****/
    /**
     * Metode que entrega la moto al usuari i elimina la moto del local de origen
     */
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
    /**
     * Metode que retorna la moto al local i la posa a disponible a mes de comprovar si hi ha desperfectes o faltes
     */
    public void RetornarMoto() {
        //demanem codi de la reserva
        vista.escriu("Introdueixi el codi de la reserva:");
        String c = vista.llegeixString();
        Local o = ((Gerent)Usuari).getLocal();
        Reserva res = o.comprovarCodi(c);
        int faltes = 0;
        //comprovem que el codi es correcte
        while(res == null) {
            vista.escriu("El codi de la reserva és incorrecte.");
            vista.escriu("Introdueixi el codi un altre cop: ");
            c = vista.llegeixString();
            res = o.comprovarCodi(c);
        }
        vista.escriu(res.toString());
        Client cl = getClient(res.getIdC());
        Moto m = res.getMoto();
        //demanem si es avariada la moto
        vista.escriu("Introdueixi l'estat de la moto(avariada/dispnible)");
        String es = vista.llegeixString();
        o.removeMoto(m);
        m.setEstat(es);
        o.addMoto(m);
        //si es avariada falta + moto avariada
        if(es.equals("avariada")) {
            res.addFalta();
            cl.addFalta();
        }
        //comprovem retard i afegim
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
    /**
     * Metode que retorna un client a partir del seu id
     * @param id id del client
     * @return objecte client
     */
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
    
    /**
     * Metode que permet gestionar un local per part d'un gerent, moure una moto d'un local al seu
     */
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

