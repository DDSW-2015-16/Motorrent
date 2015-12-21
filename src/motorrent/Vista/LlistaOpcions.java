/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Vista;

/**
 *
 * @author Albert, Arnau i Marc
 */
class LlistaOpcions
{
    /* Colors de: http://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println*/
    private final String ANSI_RESET;
    private final String ANSI_RED;
    private final String ANSI_GREEN;
    
    /* Variables cortesia */
    private final String presentacio;
    private final String exit;
    
    /* Variables para menus */
    private final String Client;
    
    private final String noClient;
        /* gerente */
    private final String gerentFirstMenu;
        /* jefe */
    private final String AdminFirstMenu;
    
    /* Variables para errores */
    private final String errorMenu;
    
    /**
     * Al inicializar LlistaOpcions
     * se definen todas las variables para poder devolver
     * la informacion deseada
     */
    public LlistaOpcions ()
    {
        /* Colors */
        ANSI_RESET = "\u001B[0m";
        ANSI_RED = "\u001B[31m";
        ANSI_GREEN = "\u001B[32m";
        
        /* Cortesia */
        presentacio = "Benvingut a Motorent!";
        exit = "Fins aviat!";
        
        /* Menus */
        Client = 
                "Menu Client: \n" +
                "Escoja la opcion deseada:\n"           +
                Cgreen ( "c" ) + " - Consultar reserves anteriors\n"         +
                Cgreen ( "r" ) + " - Fer una reserva \n"     +
                Cgreen ( "s" ) + " - Sortir";
        noClient =
                "Menu principal.\n"   +
                "Seleccioni una opció:\n"           +
                Cgreen ( "l" ) + " - Logarse\n"         +
                Cgreen ( "r" ) + " - Registrar-se\n"     +
                Cgreen ( "s" ) + " - Sortir";
                /* gerent */
        gerentFirstMenu =
                "Menu Gerent.\n"                                   +
                "Seleccioni una opció:\n"                       +
                Cgreen ( "m" ) + " - Entregar moto\n"              +
                Cgreen ( "g" ) + " - Gestió del local\n"              +
                Cgreen ( "r" ) + " - Recollir moto\n"           +
                Cgreen ( "s" ) + " - Sortir";
                /* jefe */
        AdminFirstMenu =
                "Menu Administrador.\n"                           +
                "Seleccioni una opció:\n"               +
                Cgreen ( "v" ) + " - Ver stock motos\n"     +
                Cgreen ( "g" ) + " - Generar Informe\n"   +
                Cgreen ( "s" ) + " - Sortir";
        
        /* Errores */
        errorMenu = Cred ("La entrada no ha sigut identificada.") +" Sisplau, entri el que se li demana al menú";
    }
    
    /************************ Cortesia *****************************/
    /**
     * Presentacion del programa
     * @return String de la presentacion del programa
     */
    public String getPresentacio () {return presentacio;}
    
    /**
     * Salir del programa
     * @return String de la salida del programa
     */
    public String getExit () {return exit; }
    
    /************************ Menu *****************************/
    public String getClient() {return Client;}
    
    /**
     * Menu para los usuarios no identificados
     * @return String de las opciones que tiene
     */
    public String getNoClient () {return noClient;}

/* gerente */
    /**
     * Menu principal para los gerentes
     * @return String de las opciones que tiene.
     */
    public String getGerent () {return gerentFirstMenu; }

/* jefe */
    /**
     * Menu principal del jefe
     * @return String de las opciones que tiene.
     */
    public String getAdmin () {return AdminFirstMenu; }

    /************************ Errors *****************************/
    /**
     * Mensaje d'error en seleccionar un elemento
     * @return String con las instrucciones para evitar el error
     */
    public String getErrorMenu () {return errorMenu;}
    
    /************************ Colors *****************************/
    /* Metodes privats, per a donar color */
    private String Cgreen (String e) {return ANSI_GREEN + e + ANSI_RESET;}
    private String Cred (String e) {return ANSI_RED + e + ANSI_RESET;}
}
