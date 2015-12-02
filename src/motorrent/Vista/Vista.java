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
        System.out.println
        (
                "Ha entrat al menu de logarse\n" +
                        "c - client\n" +
                        "g - gerent\n" +
                        "j - jefe\n" +
                        "s - sortir"
        );
        switch ( scanner.nextLine() )
        {
            case ("c"):
                System.out.println ("Felicitats, s'ha logat correctament!\n Falta fer classes");
                MenuCliente ();
                break;
            case ("g"):
                MenuGerente ();
                break;
            case ("j"):
                MenuJefe ();
                break;
            case ("s"):
                break;
            default:
                System.out.println ( strings.getErrorMenu() );
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
                    System.out.println ("Felicitats s'ha registrat correctament!\n Falta fer classes");
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
        while (loop)/* preguntem si ja ha fet una reserva */
            if ( controlador.HaveReserva () )
                loop = MenuClientYesReserva (); /* Ja ha fet una reserva */
            else /* encara no ha fet cap reserva */
                loop = MenuClientNoReserva ();
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
                controlador.MakeReserva();
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
}
