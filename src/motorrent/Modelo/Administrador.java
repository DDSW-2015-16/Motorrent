/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

/**
 * Clase Administrador que hereta d'usuari
 * @author Motorent
 */
public class Administrador extends Usuari{
    private String id;
    private String nom;
    /**
     * Constructor de la clase
     * @param usuari nom d'usuari
     * @param password contrasenya del administrador
     * @param id id dins del sistema de Motorent
     * @param nom nom real del administrador
     * 
     */
    public Administrador(String usuari, String password, String id, String nom) {
        super(usuari, password);
        this.id = id;
        this.nom = nom;
    }
    /**
     * Metode que formata la sortida per consola la informacio del administrador
     * @return 
     */
    public String toString() {
        String s = "";
        s += "Administrador: \n" +
                "Identificador: " + id + "\n" +
                "Nom: " + nom + "\n";
        return s;
    }
    
}
