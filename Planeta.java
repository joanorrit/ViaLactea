/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vialactea;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author jonatan.lopez.gonzalez
 */

public class Planeta implements Serializable{
    private int id;
    private Posicio pos;
    private String nom;
    //int rec_necesitat;
    //boolean colonitzable;    
    public Planeta() {
        pos = new Posicio();
        id = -1;
    }
    public Posicio getPos() {
        return pos;
    }
    // Si al final colonitzable o no, parametre bool 
    public void crear_Planeta(double ab, double ord, int newid, String name) {
        nom = name;
        id = newid;
        pos.setPosx(ab);
        pos.setPosy(ord);
    }
    public void crear_Planeta(double ab, double ord, int newid) {
        id = newid;
        pos.setPosx(ab);
        pos.setPosy(ord);
    }
    public  Posicio posicio_Planeta() {
        return pos;
    }
    public String getnom() {
        return nom;
    }
    public int getid() {
        return id;
    }
}
