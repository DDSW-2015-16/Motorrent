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
    private final String noClient;
    private final String clientYreserva;
    private final String clientNreserva;
    
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
        presentacio = "Bienvenido a Motorrent!";
        exit = "Deseamos verlo pronto por aqui";
        
        /* Menus */
        noClient =
                "No estas ni logado ni registrado.\n"   +
                "Escoja la opcion deseada:\n"           +
                Cgreen ( "l" ) + " - Logarse\n"         +
                Cgreen ( "r" ) + " - Registrarse\n"     +
                Cgreen ( "q" ) + " - Salir";
        clientNreserva =
                "Estas logeado.\n"              +
                "Escoja la opcion deseada:\n"   +
                Cgreen ( "r" ) + " - Hacer reserva\n"   +
                Cgreen ( "b" ) + " - Darse de baja\n"   +
                Cgreen ( "s" ) + " - Salir";
        clientYreserva = "";
        
        /* Errores */
        errorMenu = Cred ("La entrada no a sido identificada.") +" Profavor, entra lo que pide el menu";
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
    /**
     * Menu para los usuarios no identificados
     * @return String de las opciones que tiene
     */
    public String getNoClient () {return noClient;}
    
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
