/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

/**
 * Clase moto, que emmagatzema totes les dades d'una moto real
 * @author Leiva
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
    
    
  
    /**
     * Constructor de la classe moto 
     * @param id id de la moto
     * @param matricula matricula de la moto
     * @param marca marca de la moto
     * @param model model de la moto
     * @param color color de la moto
     * @param estat estat de la moto
     */
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
    /**
     * Metode que posa una moto a no disponible
     */
    public void setNoDisponible() {
        estat = "No Disponible";
    }
    
    public String getId() {
        return identificador;
    }
    
    public String getEstat() {
        return estat;
    }
    
    public void setEstat(String s) {
        estat = s;
    }
}
