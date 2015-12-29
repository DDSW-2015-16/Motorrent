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
        Dia = Integer.parseInt(s.substring(0,2));
        Mes = Integer.parseInt(s.substring(3,5));
        Any = Integer.parseInt(s.substring(6,10));
    }
    
    public String toString() {
        return Data;
    }

    public int getMes() {
        return Mes;
    }
    
}


