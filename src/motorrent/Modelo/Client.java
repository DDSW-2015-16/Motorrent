/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Albert, Arnau i Marc
 */
public class Client extends Usuari
{
    private boolean reserva;
    private String Nom;
    private String Cognom;
    private String DNI;
    private int faltes;
    private String Correu;
    private int Tel;
    private String Vip;
    private Adreça adr;
    private ComptaCorrent cc;
    private ArrayList<Reserva> historial = new ArrayList<Reserva>();
    private Data dataN;
    private Data dataIns;
    private String id;
    private String renovacio;
    private Reserva res;
    public Client() {
    }
    
    public Client (String id, String nom, String dni, String adreca, String usuari, String password, String vip, String renovacio, int faltes)
    {
        super(usuari,password);
        this.id = id;
        Nom = nom;
        DNI = dni;
        adr = new Adreça(adreca);
        Vip = vip;
        this.renovacio = renovacio;
        this.faltes = faltes;
        
    }
    public String toString() {
        String s = "";
        s += "Client: \n" +
                "Identificador: " + id + "\n" +
                "Nom: " + Nom + "\n" +
                "DNI: " + DNI + "\n" +
                "Adreça: " + adr + "\n" +
                "Faltes: " + faltes +"\n" +
                "VIP :" + Vip + "\n";
        
        return s;
    }
    /*Afegir al diagrama de classes*/
    public boolean hasReserva(){
        if (res != null){
            return true;
        }else{
            return false;
        }
    }
    
    public String getUser() {
        return super.getUsuari();
    }
    public String getPass() {
        return super.getPassword();
    }
    public int getFaltes() {
        return faltes;
    }
    public String getId() {
        return id;
    }
    public Reserva getReserva() {
        return res;
    }
    public void crearReserva(String idLO, String idM, String idD, String dR, String hR, String dD, String hD, String id, Moto m) {
        res = new Reserva();
        res.setOrigen(idLO);
        res.setM(idM);
        res.setDataD(dD);
        res.setDataR(dR);
        res.setDesti(idD);
        res.setHoraD(hD);
        res.setHoraR(hR);
        res.setId(id);
        res.setMoto(m);
        
    }
    public void addReservaHistorial(String id,String moto, int cost, int falta, String local_inici,String hora_inici,String fecha_inici, String local_fi,String hora_fi,String fecha_fi) {
        Reserva r = new Reserva();
        r.setCost(cost);
        r.setDataD(fecha_fi);
        r.setHoraD(hora_fi);
        r.setDesti(local_fi);
        r.setId(id);
        r.setOrigen(local_inici);
        r.setDataR(fecha_inici);
        r.setHoraR(hora_inici);
        r.setM(moto);
        r.setFalta(falta);
        historial.add(r);
        faltes += falta;
  
    }
    public String imprimirHistorial() {
        String h = "";
        for(int i = 0; i < historial.size(); ++i) {
            h += historial.get(i) + "\n";
        }  
        return h;
    }
    
    public String imprimirReservaActiva() {
        String s = "";
        s += res;
        return s;
    }


   
}
