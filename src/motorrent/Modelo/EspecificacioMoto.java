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
public class EspecificacioMoto {
    private String marca;
    private String model;
    
    public EspecificacioMoto (String marcaExtern, String modelExtern)
            {
                marca = marcaExtern;
                model = modelExtern;
            }
    
    public boolean esIgual (String marcaExtern, String modelExtern)
    { return marca.equals(marcaExtern) && model.equals(modelExtern); }
    
    @Override
    public String toString ()
    { return "marca: " + marca + "\nmodel: "+ model + "\n"; }
}
