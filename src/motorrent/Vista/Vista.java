/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Vista;

/* Paquets propis */
import java.util.Date;
import motorrent.Controlador.Motorent;

/* Paquets de java */
import java.util.Scanner;

/**
 *
 * @author Albert, Arnau i Marc
 */
public class Vista
{
    private Scanner scanner;
    private Motorent controlador;
    private LlistaOpcions strings;
    
    public Vista ()
    {
        scanner = new Scanner ( System.in );
        strings = new LlistaOpcions ();
        controlador = new Motorent ("data/MotoRent.xml");
    }
    
    private void Logarse ()
    {
        escriu("Ha entrat al menu de logarse" );
        escriu ("Introdueixi el seu nom d'usuari: ");
        String us = "";
        String ps = "";
        us = llegeixString();
        escriu("Introdueixi la seva contrasenya:");
        ps = llegeixString();
        if(controlador.checkUser(us, ps)) {
            switch(controlador.typeUser()) {
                case ("c"):
                    MenuCliente();
                    break;
                case ("e"):
                    escriu("Acces denegat. Vosté té 3 o més faltes acumulades.");
                case ("g"):
                    MenuGerente();
                    break;
                case ("a"):
                    MenuJefe();
                    break;
            }
        }
        else {
            escriu("Usuari o contrasenya incorrecta, torna a provar-ho");
            MenuNoIdentificado();
        }
    }
    
    public void MenuNoIdentificado ()
    {
        boolean loop = true;
        escriu ( strings.getPresentacio() );
        while (loop)
        {
            escriu ( strings.getNoClient() );
            switch ( llegeixString() )
            {
                case ( "l" ):
                    Logarse ();
                    break;
                case ( "r" ):
                    demanarDades();
                    escriu("S'ha registrat correctament!");
                    break;
                case ( "q" ):
                    loop = false;
                    escriu( strings.getExit() );
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
    }
    
    /***************          USUARIOS          ********************            USUARIOS      ***************************************/
    private void MenuCliente ()
    { /* No hay ningun printf, ja que los hacen los submenus corresponientes */
        boolean loop = true;
        while (loop)
        {
            escriu ( strings.getClient() );
            switch ( llegeixString() )
            {
                case ( "c" ):
                    escriu (controlador.ImprimirH());
                    break;
                case ( "r" ):
                    if(controlador.hasReserva()) {
                        escriu("Ja té una reserva feta");
                        break;
                    }
                    else {
                        ferReserva();
                    }
                case ( "m" ):
                    escriu ("Modificar reserva");
                    break;
                case ( "f" ):
                    escriu("Consultar faltes");
                    break;
                case ("d"):
                    escriu("Consultar dades");
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
        
    }
    /* Entran los usuarios que han echo una reserva */
    private boolean MenuClientYesReserva ()
    {
        System.out.println (strings.getClienteConReserva());
        switch ( scanner.nextLine() )
        {
            case ( "m" ):
                System.out.println ( "Ha modificat exitosament la reserva" );
                break;
            case ( "b" ):
                System.out.println ( "Fins aviat, aqui se donara de baixa" );
                return false;
            case ( "s" ):
                return false;
            default:
                System.out.println ( strings.getErrorMenu() );
        }
        return true;
    }
    /* Entran los usuarios que han echo una reserva */
    private boolean MenuClientNoReserva ()
    {
        System.out.println ( strings.getClienteSinReserva() );
        switch ( scanner.nextLine() )
        {
            case ( "r" ):
                System.out.println ( "S'ha registrat correctament!" );
                break;
            case ( "b" ):
                System.out.println ( "Fins aviat, aqui se donara de baixa" );
                return false;
            case ( "s" ):
                return false;
            default:
                System.out.println ( strings.getErrorMenu() );
        }
        return true;
    }
    
    /************* GERENTE ********************* GERENTE ******************************************/
    private void MenuGerente ()
    {
        boolean loop = true;
        while (loop)
        {
            escriu ( strings.getGerent() );
            switch ( llegeixString() )
            {
                case ( "m" ):
                    escriu ("Registrada la moto");
                    break;
                case ( "g" ):
                    escriu ("Gestionat el local");
                    break;
                case ( "r" ):
                    escriu ("Comprovat la reserva");
                    break;
                case ( "c" ):
                    escriu ("Comprovat l'estoc de les motos");
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
    }
    
    /********** JEFE ************ JEFE ************ JEFE **********/
    private void MenuJefe ()
    {
        boolean loop = true;
        while (loop)
        {
            escriu (strings.getJefe());
            switch (llegeixString())
            {
                case ( "v" ):
                    escriu ("Mostrar el stock de motos");
                    escriu(controlador.ImprimirLocalsMotos());
                    break;
                case ( "g" ):
                    escriu ("Gestionado los locales");
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
    }
    
    /******** DEMANAR DADES PEL REGISTRE ************/
    private void demanarDades() {
        String u = "";
        String p = "";
        String d = "";
        String a = "";
        String n = "";
        String opcio = "";
        escriu(
                "Seleccioni una opció: \n " +
                "a - Registrar-se com a Administrador \n "+
                "b - Registrar-se com a Gerent \n"+
                "c - Registrar-se com a Client"
        );
        opcio = llegeixString();
        escriu("Introdueixi el nom d'usuari que desitji :");
        while(controlador.existeixUsuari(u = llegeixString())) {
             escriu("L'usuari ja existeix, torna a introduir un altre nom d'usuari.");
        }
        escriu("Introdueix la contrasenya: ");
        p = llegeixString();
        escriu("Introdueix el nom i cognoms: ");
        n = llegeixString();
        switch(opcio) {
            case ("a"):
                break;
            case ("b"):
                break;
            case("c"): 
                escriu("Introdueix el seu DNI: ");
                d = llegeixString();
                escriu("Introdueix la seva adreça ");
                a = llegeixString();
                controlador.registreClient(u, p, n, d, a);
                break;
        }
    }
    private void ferReserva(){
        String idLD = "";
        String idLO = "";
        String idM = "";
        String dR = "";
        String dD = "";
        String hR = "";
        String hD = "";
        escriu("Seleccioni l'identificador (ID) del local origen: ");
        escriu(controlador.ImprimirLocals());
        idLO = llegeixString();
        controlador.SeleccionarLocal(idLO);
        escriu("Seleccioni l'identificador de la moto que desijta: ");
        escriu(controlador.ImprimirMotos());
        idM = llegeixString();
        controlador.SeleccionarMotoLocal(idM);
        escriu("Seleccioni l'identificador (ID) del local desti: ");
        escriu(controlador.ImprimirLocals());
        idLD = llegeixString();
        controlador.SeleccionarLocal(idLD);
        while(!controlador.checkCapacitat()) {
            escriu("No hi ha espai suficient");
            escriu("Seleccioni un altre local:");
            escriu(controlador.ImprimirLocals());
            idLD = llegeixString();
            controlador.SeleccionarLocal(idLD);
        }
        escriu("Escriu la data de recollida de la moto:");
        dR = llegeixString();
        escriu("Escriu l'hora de recollida de la moto:");
        hR = llegeixString();
        escriu("Escriu la data de devolucio de la moto:");
        dD = llegeixString();
        escriu("Escriu l'hora de devolucio de la moto:");
        hD = llegeixString();
        controlador.ferReserva(idLO, idM, idLD, dR, hR, dD, hD);
        escriu(controlador.ImprimirReservaClient());
    }
    
    /**
     * ESCRIU I LLEGIR
     *  
     */
    
    public void escriu(String s){
         System.out.println(s);
    }
    
    public void escriu(int i) {
        System.out.println(i);
    }
    
    public void escriu(float f) {
        System.out.println(f);
    }
    
    public void escriu(Date d) {
        System.out.println(d);
    }
    
    public int llegeixInt() {
        return scanner.nextInt();
    }
    
    public String llegeixString() {
        return scanner.nextLine();
    }
    
    

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}   