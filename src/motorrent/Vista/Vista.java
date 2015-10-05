/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Vista;

/* Paquets propis */
import motorrent.Controlador.Controlador;

/* Paquets de java */
import java.util.Scanner;

/**
 *
 * @author Albert, Arnau i Marc
 */
public class Vista
{
    private Scanner scanner;
    private Controlador controlador;
    private LlistaOpcions strings;
    
    public Vista ()
    {
        scanner = new Scanner ( System.in );
        strings = new LlistaOpcions ();
        controlador = new Controlador ();
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
                    break;
                case ( "r" ):
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
    
    private void MenuCliente ()
    {
        boolean loop = true;
        while (loop)
        { /* preguntem si ja ha fet una reserva */
            if ( controlador.HaveReserva () )
                loop = MenuClientYesReserva (); /* Ja ha fet una reserva */
            else /* encara no ha fet cap reserva */
                loop = MenuClientNoReserva ();
        }
    }
    private boolean MenuClientYesReserva ()
    {
        System.out.println ();
        switch ( scanner.nextLine() )
        {
            case ( "s" ):
                return false;
            default:
                System.out.println ( strings.getErrorMenu() );
        }
        return true;
    }
    private boolean MenuClientNoReserva ()
    {
        System.out.println ();
        switch ( scanner.nextLine() )
        {
            case ( "s" ):
                return false;
            default:
                System.out.println ( strings.getErrorMenu() );
        }
        return true;
    }
}
