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
}
