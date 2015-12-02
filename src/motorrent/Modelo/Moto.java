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
    
    public Moto (String id, String matricula, String marca, String model, String color, String estat)
    {
        identificador = id;
        this.matricula = matricula;
        this.color = color;
    }
    
    @Override
    public String toString() {
        String s = "";
        s += "Identificador: " + identificador + "\n" +
                "Matricula: " + matricula + "\n" +
                "Color: " + color + "\n";
        return s;
                
    }
}
