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
public class Data {
    private int Dia;
    private int Mes;
    private int Any;
    private String Data;
    
    
    public Data(String s) {
        
        Data = s;
    }
    
    public String toString() {
        return Data;
    }

    public int getMes() {
        return Mes;
    }
    
}


