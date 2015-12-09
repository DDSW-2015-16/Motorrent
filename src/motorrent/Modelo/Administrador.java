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
public class Administrador extends Usuari{
    private String id;
    private String nom;
    
    public Administrador(String usuari, String password, String id, String nom) {
        super(usuari, password);
        this.id = id;
        this.nom = nom;
    }
    
    public String toString() {
        String s = "";
        s += "Administrador: \n" +
                "Identificador: " + id + "\n" +
                "Nom: " + nom + "\n";
        return s;
    }
    
}
