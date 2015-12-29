/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Vista;

/* Paquets propis */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        controlador = new Motorent ("data/MotoRent.xml", this);
    }
    
     /*************** MENU PRINCIPAL **************/
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
                    controlador.login();
                    break;
                case ( "r" ):
                    controlador.Registre();
                    escriu("S'ha registrat correctament!");
                    break;
                case ( "s" ):
                    loop = false;
                    escriu( strings.getExit() );
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
    }
  
   
    
    /*************** MENU USUARIS  ***********/
    public void MenuCliente ()
    { /* No hay ningun printf, ja que los hacen los submenus corresponientes */
        boolean loop = true;
        while (loop)
        {
            escriu ( strings.getClient() );
            switch ( llegeixString() )
            {
                case ( "c" ):
                    break;
                case ( "r" ):
                    controlador.ferReserva();
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
        
    }

    /************* MENU GERENT ********/
    public void MenuGerente ()
    {
        boolean loop = true;
        while (loop)
        {
            escriu ( strings.getGerent() );
            switch ( llegeixString() )
            {
                case ( "m" ):
                    controlador.entregarMoto();
                    break;
                case ( "g" ):
                    controlador.gestionarLocal ();
                    break;
                case ( "r" ):
                    controlador.RetornarMoto();
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
    }
    
    /********** MENU ADMINISTRADOR **********/
    public void MenuAdministrador ()
    {
        boolean loop = true;
        while (loop)
        {
            escriu (strings.getAdmin());
            switch (llegeixString())
            {
                case ( "v" ):
                    controlador.ImprimirLocalsMotos();
                    break;
                case ( "g" ):
                    escriu ("");
                    controlador.generarInforme();
                    break;
                case ( "s" ):
                    loop = false;
                    break;
                default:
                    escriu ( strings.getErrorMenu() );
            }
        }
    }

    
     
    /***** ESCRIU I LLEGIR ***/
    
    public void escriu(String s){
         System.out.println(s);
    }
    
    public void escriu(int i) {
        System.out.println(i);
    }
    
    public void escriu(float f) {
        System.out.println(f);
    }
    
    public void escriu(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String d = dateFormat.format(date);
        System.out.println(d);
    }
    
    public int llegeixInt() {
        return scanner.nextInt();
    }
    
    public String llegeixString() {
        return scanner.nextLine();
    }
    
    public Date LlegeixDataSistema() {
        Date date = new Date();
        return date;
    }
    
    
    
    

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}   
