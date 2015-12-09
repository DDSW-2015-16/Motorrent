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
public class Gerent extends Usuari{
    private String Identificador;
    private Local local;
    private String nom;
    
    public Gerent(String usuari, String password, String id, String nom) {
        super(usuari, password);
        Identificador = id;
        this.nom = nom;
    }
    
    public String toString() {
        String s = "";
        s += "Gerent: \n" +
                "Identificador: " + Identificador + "\n" +
                "Nom: " + nom + "\n";
        return s;
    }
}
