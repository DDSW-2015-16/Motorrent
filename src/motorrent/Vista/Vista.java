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
        controlador = new Motorent ("data/MotoRent.xml");
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
    
    /*********** MENU LOGIN ************/
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
                    MenuAdministrador();
                    break;
            }
        }
        else {
            escriu("Usuari o contrasenya incorrecta, torna a provar-ho");
            MenuNoIdentificado();
        }
    }
   
    
    /*************** MENU USUARIS  ***********/
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
                        break;
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

    /************* MENU GERENT ********/
    private void MenuGerente ()
    {
        boolean loop = true;
        while (loop)
        {
            escriu ( strings.getGerent() );
            switch ( llegeixString() )
            {
                case ( "m" ):
                    entregarMoto();
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
    
    /********** MENU ADMINISTRADOR **********/
    private void MenuAdministrador ()
    {
        boolean loop = true;
        while (loop)
        {
            escriu (strings.getAdmin());
            switch (llegeixString())
            {
                case ( "v" ):
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
                "b - Registrar-se com a Gerent \n "+
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
    
    /********** RESERVA MOTO *****/
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
        escriu("Seleccioni l'identificador(ID) de la moto que desijta: ");
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
        escriu("Escriu la data de recollida de la moto(DD/MM/AAAA):");
        dR = llegeixString();
        escriu("Escriu l'hora de recollida de la moto(HH/MM/SS):");
        hR = llegeixString();
        escriu("Escriu la data de devolucio de la moto(DD/MM/AAAA):");
        dD = llegeixString();
        escriu("Escriu l'hora de devolucio de la moto(HH/MM/SS):");
        hD = llegeixString();
        controlador.ferReserva(idLO, idM, idLD, dR, hR, dD, hD);
        escriu(controlador.ImprimirReservaClient());
    }
    
    /********** ENTREGAR MOTO ****/
    private void entregarMoto() {
        escriu("Entra el codi de la reserva");
        String codi;
        codi = llegeixString();
        //Comprovar codigo correcto
        if(controlador.entregarMoto(codi)) {
            escriu("Entrega feta correctament");
        }
        else {
            escriu("Codi de la resera incorrecta");
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
