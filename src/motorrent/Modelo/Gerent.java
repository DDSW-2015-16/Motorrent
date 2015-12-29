/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

/**
 * Classe encarregada de gestionar els gerents, hereta d'usuari
 * @author Leiva
 */
public class Gerent extends Usuari{
    private String Identificador;
    private Local local;
    private String nom;
    /**
     * Constructor de la classe gerent
     * @param id id del gerent
     * @param nom nom del gerent
     * @param usuari nom de usuari del gerent
     * @param password password del gerent
     */
    public Gerent(String usuari, String password, String id, String nom) {
        super(usuari, password);
        Identificador = id;
        this.nom = nom;
    }
    /**
     * Metode que formata la sortida per consola
     * @return 
     */
    public String toString() {
        String s = "";
        s += "Gerent: \n" +
                "Identificador: " + Identificador + "\n" +
                "Nom: " + nom + "\n" +
                "Local" + local + "\n";
        return s;
    }
    
    public void setLocal (Local l)
    { local = l; }
    
    public String  getID ()
    { return Identificador; }

    public Local getLocal() {
        return local;
    }
}
