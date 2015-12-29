/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorrent.Modelo;

import java.util.ArrayList;

/**
 * Clase client que hereta de usuari
 * @author Leiva
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
    /**
     * Constructor de la clase Client
     * @param id id del usuari
     * @param nom nom del usuari
     * @param dni dni del usuari
     * @param adreca adreca del usuari
     * @param usuari nom de usuari
     * @param password password del usuari
     * @param vip si es o no vip
     * @param renovacio 
     * @param faltes num de faltes que té
     */
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
    /**
     * Metode que comprova si te reserva
     * @return true or false si te o no reserva
     */
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
    /**
     * Metode que crea una reserva i la retorna
     * @return la reserva
     */
    public Reserva crearReserva() {
        res = new Reserva();
        return res;
    }
    /**
     * Metode que afegeix una reserva al historial de reserves del client
     * @param res 
     */
    public void addReserva(Reserva res) {
        historial.add(res);
    }
    /**
     * Metode que afegeix les dades a una reserva
     * @param id id de la reserva
     * @param client id del client que te la reserva
     * @param moto id moto escollida
     * @param cost cost reserva
     * @param falta faltes que ha acumulat el client
     * @param local_inici local on ha recollit la moto
     * @param hora_inici hora inici del prestec
     * @param fecha_inici dia inici prestec
     * @param local_fi local on ha retornar la moto
     * @param hora_fi hora fi prectec
     * @param fecha_fi data fi prectes
     */
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
    /**
     * Metode que retorna la reserva actuva
     * @return String amb les dades de la reserva
     */
    public String imprimirReservaActiva() {
        String s = "";
        s += res;
        return s;
    }
    /**
     * Metode que genera el informe a partir del mes
     * @param mes mes en numero que volem
     * @return string amb l'informe
     */
    public String getInforme(String mes){
        String informe = "";
        for (Reserva u:historial){
            if(Integer.parseInt(mes) == u.getMesDataR()){
               informe += this.getUser() + "\n";
               informe += u.toString() + "\n";
            }
        }
        return informe;
    }
    
    public void setPassword(String s) {
        super.setPassword(s);
    }
    
    public void setUser(String s) {
        super.setUser(s);
    }
    /**
     * Metode que afegeix una falta
     */
    public void addFalta() {
        ++faltes;
    }

    
   
}
