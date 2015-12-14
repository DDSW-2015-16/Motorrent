/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

/**
 *
 * @author Marc
 */
public class Moto {
    private String identificador;
    private double kilometres;
    private boolean disponible;
    private Estat est;
    private EspecificacioMoto esp;
    private String matricula;
    private String color;
    private String estat;
    
    
    public Moto () {
        
    }
    public Moto (String id, String matricula, Object especificacioMoto, String color, String estat)
    {
        identificador = id;
        this.matricula = matricula;
        this.color = color;
        this.estat = estat;
        esp = (EspecificacioMoto) especificacioMoto;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "Identificador: " + identificador + "\n" +
                "Matricula: " + matricula + "\n" +
                "Color: " + color + "\n" +
                esp +
                "Estat: " + estat + "\n";   
        return s;
                
    }
    
    public void setNoDisponible() {
        estat = "No Disponible";
    }
    
    public String getId() {
        return identificador;
    }
}
