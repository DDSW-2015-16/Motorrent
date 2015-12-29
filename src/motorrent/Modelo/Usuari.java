/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

/**
 * Clase usuari pare de la que hereten els tres tipus d'usuari al sistema
 * @author Leiva
 */
public class Usuari {
    private String user;
    private String password;
    
    public Usuari() {
        
    }
    public Usuari(String usuari, String password) {
        user = usuari;
        this.password = password;
    }
    public String getUsuari() {
        return user;
    }
    
    public String getPassword() {
        return password;
    }
    /**
     * Metode que comprova que el login d'un usuari es correcte
     * @param us nom d'usuari a comprovar
     * @param ps password del usuari
     * @return true o false si el login es correcte
     */
    public boolean checkUser(String us, String ps) {
        boolean check = false;
        if(getUsuari().equals(us)) {
            if(getPassword().equals(ps)) {
                check = true;
            } 
        }
        return check;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Metode que comprova que un usuari existeix en el sistema
     * @param us usuari que volem comprovar
     * @return true o false segons si existeix o no l'usuari
     */
    public boolean existeix_usuari(String us) {
        if(getUsuari().equals(us)) {
            return true;
        }
        else {
            return false;
        }
    }
}
