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
    private String adr;
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
        adr = adreca;
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
    
    public void rmReservaActiva() {
        res = null;
    }
    
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setFaltes(int faltes) {
        this.faltes = faltes;
    }

    public void setVip(String Vip) {
        this.Vip = Vip;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setId(String id) {
        this.id = id;
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
    public void setReserva(Reserva r) {
        res = r;
    }
    public Reserva crearReserva() {
        res = new Reserva();
        return res;
    }
    public void addReserva(Reserva res) {
        historial.add(res);
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
    
    
    public void setPassword(String s) {
        super.setPassword(s);
    }
    
    public void setUser(String s) {
        super.setUser(s);
    }

   
}
