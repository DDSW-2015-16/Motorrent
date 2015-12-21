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
    
    public boolean existeix_usuari(String us) {
        if(getUsuari().equals(us)) {
            return true;
        }
        else {
            return false;
        }
    }
}
