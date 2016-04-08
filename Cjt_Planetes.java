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
public class Cjt_Planetes implements Serializable {
     private Planeta[] planetes;
     private int id;
     private int numero_planetes;
     private static final long serialVersionUID = 2292067090819744259L;
     public Cjt_Planetes(int numplanetas) {
         planetes = new Planeta[numplanetas];
         id = -1;
         numero_planetes = numplanetas;
     }
     public boolean is_Empty () {
         int cont = 0;
         for (int i = 0; i < numero_planetes; ++i) {
             if (planetes[i] == null) ++cont;
         }
         return (numero_planetes == cont);
     }
     public boolean valida_planetes(Planeta P) {
         boolean posequal = false;
         for (int i = 0; i < numero_planetes && !posequal; ++i) {
            if (planetes[i] != null) {
               if (planetes[i].getPos().getPosx() == P.getPos().getPosx() && planetes[i].getPos().getPosy() == P.getPos().getPosy()) {
                       posequal = true;
               }
               else if (planetes[i].getnom().equals(P.getnom())) posequal = true;
            }
        }
         return posequal;
     }
     public void afegir_Planeta(Planeta P, int id) {
        planetes[id] = P;
     }
     public Planeta num_Planeta(int num) {
         return planetes[num];
     }
     public void llegir_Planetes() {
        Scanner in = new Scanner(System.in);
        boolean valid;
        String nom;
        double abs, ord;
        for(int i=0; i < numero_planetes; ++i) {
             System.out.println("Nom y posicio del planeta amb id " + i + ":");
             Planeta p = new Planeta();
             nom = in.next();
             abs = in.nextFloat();
             ord = in.nextFloat();
             p.crear_Planeta(abs, ord, i, nom);
             valid = valida_planetes(p);
             if (!valid) planetes[i] = p;
             else {
                 System.out.println("Error al crear planeta, introdueixi'l de nou");
                 --i;
             }
         }
     }
     public int tamcjt() {
         return numero_planetes;
     }
     public Planeta[] getPlanetes() {
         return planetes;
     }
     public int getIdPlaneta(String nom) {
         boolean trobat = false;
         int ide = -1;
         for (int i = 0; i < planetes.length && !trobat; ++i) {
              System.out.println("entro id = " + i);
             if (planetes[i].getnom().equals(nom)) {
                 trobat = true;
                 ide = planetes[i].getid();
             }
         }
         return ide; 
     }
     public void escriuplanetes() {
         for (int i = 0; i < planetes.length; ++i) {
             System.out.println("El planeta amb id " + planetes[i].getid() + " es diu " + planetes[i].getnom() + " i es troba a la posicio " + planetes[i].getPos().getPosx() + " , " + planetes[i].getPos().getPosy());
             
         }
     }

    public double getPosX(int i) {
        return planetes[i].getPos().getPosx(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNomPlaneta(int i) {
        return planetes[i].getnom(); //To change body of generated methods, choose Tools | Templates.
    }

    public double getPosY(int i) {
        return planetes[i].getPos().getPosy();//To change body of generated methods, choose Tools | Templates.
    }
}

