/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vialactea;

import java.io.Serializable;

/**
 *
 * @author Jonatan
 */
public class Recurs implements Serializable{
    private int id;
    private String nom;
    public Recurs() {
        id = -1;
        nom = null;
    }
    public int getIdRec() {
        return id;
    }
    public void setIdRec(int idrec, String name) {
        id = idrec;
        nom = name;
    }
    
    public void setnom(String name) {
        nom = name;
    }
    
    public void setid(int ide) {
        id = ide;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
}
