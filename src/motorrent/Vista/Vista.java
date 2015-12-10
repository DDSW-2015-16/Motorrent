/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Vista;

/* Paquets propis */
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
        System.out.println("Ha entrat al menu de logarse\n" );
        System.out.println ("Introdueixi el seu nom d'usuari: ");
        String us;
        String ps;
        us = scanner.nextLine();
        System.out.println("Introdueixi la seva contrasenya:");
        ps = scanner.nextLine();
        if(controlador.checkUser(us, ps)) {
            switch(controlador.typeUser()) {
                case ("c"):
                    MenuCliente();
                    break;
                case ("e"):
                    System.out.println("Acces denegat. Vosté té 3 o més faltes acumulades.");
                case ("g"):
                    MenuGerente();
                    break;
                case ("a"):
                    MenuJefe();
                    break;
            }
        }
        else {
            System.out.println("Usuari o contrasenya incorrecta, torna a provar-ho");
            MenuNoIdentificado();
        }
    }
    
    public void MenuNoIdentificado ()
    {
        boolean loop = true;
        System.out.println ( strings.getPresentacio() );
        while (loop)
        {
            System.out.println ( strings.getNoClient() );
            switch ( scanner.nextLine() )
            {
                case ( "l" ):
                    Logarse ();
                    break;
                case ( "r" ):
                    demanarDades();
                    System.out.print(controlador.ImprimirUsuaris());
                    MenuCliente ();
                    break;
                case ( "q" ):
                    loop = false;
                    System.out.println ( strings.getExit() );
                    break;
                default:
                    System.out.println ( strings.getErrorMenu() );
            }
        }
    }
    
    /***************          USUARIOS          ********************            USUARIOS      ***************************************/
    private void MenuCliente ()
    { /* No hay ningun printf, ja que los hacen los submenus corresponientes */
        boolean loop = true;
        while (loop)
        {
            System.out.println ( strings.getClient() );
            switch ( scanner.nextLine() )
            {
                case ( "c" ):
                    System.out.println ("Consultar reserva");
                    break;
                case ( "r" ):
                    System.out.println ("Fer reserva");
                    if(controlador.hasReserva()) {
                        System.out.println("Ja té una reserva feta");
                        break;
                    }
                    else {
                        System.out.println("Pot fer una reserva");
                    }
                case ( "m" ):
                    System.out.println ("Modificar reserva");
                    break;
                case ( "f" ):
                    System.out.println ("Consultar faltes");
                    break;
                case ("d"):
                    System.out.println("Consultar dades");
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    System.out.println ( strings.getErrorMenu() );
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
            System.out.println ( strings.getGerent() );
            switch ( scanner.nextLine() )
            {
                case ( "m" ):
                    System.out.println ("Registrada la moto");
                    break;
                case ( "g" ):
                    System.out.println ("Gestionat el local");
                    break;
                case ( "r" ):
                    System.out.println ("Comprovat la reserva");
                    break;
                case ( "c" ):
                    System.out.println ("Comprovat l'estoc de les motos");
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    System.out.println ( strings.getErrorMenu() );
            }
        }
    }
    
    /********** JEFE ************ JEFE ************ JEFE **********/
    private void MenuJefe ()
    {
        boolean loop = true;
        while (loop)
        {
            System.out.println (strings.getJefe());
            switch (scanner.nextLine())
            {
                case ( "v" ):
                    System.out.println ("Mostrado el stock de motos");
                    break;
                case ( "g" ):
                    System.out.println ("Gestionado los locales");
                    System.out.println(controlador.ImprimirLocals());
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    System.out.println ( strings.getErrorMenu() );
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
        System.out.println(
                "Seleccioni una opció: \n " +
                "a - Registrar-se com a Administrador \n "+
                "b - Registrar-se com a Gerent \n"+
                "c - Registrar-se com a Client"
        );
        opcio = scanner.nextLine();
        System.out.println("Introdueixi el nom d'usuari que desitji :");
        while(controlador.existeixUsuari(u = scanner.nextLine())) {
             System.out.println("L'usuari ja existeix, torna a introduir un altre nom d'usuari.");
        }
        System.out.println("Introdueix la contrasenya: ");
        p = scanner.nextLine();
        System.out.println("Introdueix el nom i cognoms: ");
        n = scanner.nextLine();
        if(opcio.equals("a")) {
            
        }
        if(opcio.equals("b")) {
            
        }
        if(opcio.equals("c")) {
            System.out.println("Introdueix el seu DNI: ");
            d = scanner.nextLine();
            System.out.println("Introdueix la seva adreça ");
            a = scanner.nextLine();
            controlador.registreClient(u, p, n, d, a);
        }
    }
    private void ferReserva(){
        
    }
    
    
    public void escriu(String s){
         System.out.println(s);
     }

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}   