/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vialactea;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author macia & jonatan
 */
public class Cjt_Recursos implements Serializable {
    private Recurs[] rectotal;
    private int nrecursos;
    private  static Recurs[] recursos;
    private static final long serialVersionUID = 2528657269289176357L;
    
    public  Cjt_Recursos(int nrec) {
        nrecursos = nrec;
        recursos = new Recurs[nrecursos];
        rectotal = new Recurs[nrecursos];
    }
    public boolean validaVectUniv(String nom) {
        boolean repetit = false;
        String aux;
        for (int i = 0; i < nrecursos && !repetit; ++i) {
            if (rectotal[i] != null){
                aux = rectotal[i].getNom();
                if(aux.equals(nom)){
                    repetit = true;
                }
                else repetit = false;
            }
            else repetit = false;
        }
        return repetit;
    }
    public void afegirRecursosuniv(String nom, int id) {
        Recurs r = new Recurs();
        r.setIdRec(id, nom);
        rectotal[id] = r;
    }
    
    public int buscaId(String nom) {
        int id = -1;
        boolean trobat = false;
        for (int i = 0; i < nrecursos && !trobat; ++i) {
            if (rectotal[i].getNom().equals(nom)) {
                id = i;
                trobat = true;
            }
        }
        return id;
    }
    
    public void afegirRecursos(int i,String nom) {
          System.out.println(recursos.length);
          recursos[i].setIdRec(i,nom);
          System.out.println(i);

    }
    public int getNrecusrsos() {
        return nrecursos;
    }
    public void Afegir_Nrecurs(int n){
        nrecursos = n;
    }
    
    public Recurs getRecurs(int id) {
        return rectotal[id];
    }
    public String getRecursnom(int id) {
        return rectotal[id].getNom();
    }
 }