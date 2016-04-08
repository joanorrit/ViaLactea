/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vialactea;

import java.util.Scanner;

/**
 *
 * @author joanorrit
 */
public class Solucio {
    private int [] solucio;
    private double [][] planetes;
    private double [][] paquets;
    private Algoritmo algoritme;
    
    public Solucio (double [][] m1, double [][] m2){
        this.planetes = m1;
        this.paquets = m2;
    }

    public void crear_solucio(){
        int [] prediccio = new int [0];
        algoritme = new Algoritmo();
        solucio = algoritme.calcular(planetes, paquets, prediccio,10);
    }
    
    public void llistar_vector(){
        for (int i=0; i<solucio.length; i++){
            System.out.println("Al planeta " + i + " hi trobem el paquet " + solucio[i]);
        }
    }
    
     public void crear_solucio_manualment(int Nplanetes, int Npaquets, Scanner in){
        int paquet; 
        int paquets_assignats = 0;
        solucio = new int [Nplanetes];
        for (int i=0; i<Nplanetes; i++){
            solucio[i] = Nplanetes;
        }
        for (int i=0; i<Nplanetes && paquets_assignats!=Npaquets; i++){
            boolean repetit = true;
            while(repetit){
                System.out.println("Introdueixi un paquet a assignar al planeta" + i + " si no hi vol afegir cap paquet premi -1");
                paquet = in.nextInt();
                repetit = false;
                for (int j=0; j<Nplanetes && !repetit && paquet!=-1; j++){
                    repetit = (solucio[j]==paquet) || (paquet<0 && paquet>=Npaquets);
                }
                
                if (paquet == -1) solucio[i] = Npaquets;
                if (repetit) System.out.println("Error aquest paquet ja ha estat assignat");
                if (paquet>Npaquets-1 || paquet <-1) {
                    System.out.println("Error, paquet amb identficador inexistent");
                    repetit = true;
                }
                if (!repetit && paquet!=-1) solucio[i]=paquet;
                
            }
        }
    }
    
    public void llistar_solucio(Cjt_Planetes planetes, Cjt_Recursos recursos, Paquet paquets, int Npaquets){
        String nom_planeta;
        String nom_recurs;
        //int id_recurs;
        double posX;
        double posY;
        boolean [][] matriu_paquets = paquets.get_matriu();
        for(int i=0; i<solucio.length; ++i){
            nom_planeta = planetes.num_Planeta(i).getnom();
            Posicio posicio = planetes.num_Planeta(i).getPos();
            posX = posicio.getPosx();
            posY = posicio.getPosy();
            if (solucio[i]>Npaquets-1){
                System.out.println("El planeta " + i + " amb nom " + nom_planeta + " i posicio " + posX + "," + posY + " no conte cap paquet");
            }
            else {
                System.out.print("El planeta " + i + " amb nom " + nom_planeta + " i posicio " + posX + "," + posY + " conte el paquet amb id " + solucio[i] + " que conte els recursos: ");
                for (int j=0; j<matriu_paquets[0].length; j++){
                    if (matriu_paquets[solucio[i]][j]) { 
                        nom_recurs = recursos.getRecurs(j).getNom();
                        System.out.print(nom_recurs + " ");
                    }
                }
                System.out.println();
            }
        }
    }
    
    public String LlistarSolucio(Cjt_Planetes planetes, Cjt_Recursos recursos, Paquet paquets, int Npaquets){
        String nom_planeta;
        String nom_recurs;
        //int id_recurs;
        double posX;
        double posY;
        String s = "";
        boolean [][] matriu_paquets = paquets.get_matriu();
        for(int i=0; i<solucio.length; ++i){
            nom_planeta = planetes.num_Planeta(i).getnom();
            Posicio posicio = planetes.num_Planeta(i).getPos();
            posX = posicio.getPosx();
            posY = posicio.getPosy();
            if (solucio[i]>Npaquets-1){
                s = s + "El planeta " + i + " amb nom " + nom_planeta + " i posicio " + posX + "," + posY + " no conte cap paquet \n";
            }
            else {
                s = s + "El planeta " + i + " amb nom " + nom_planeta + " i posicio " + posX + "," + posY + " conte el paquet amb id " + solucio[i] + " que conte els recursos: ";
                for (int j=0; j<matriu_paquets[0].length; j++){
                    if (matriu_paquets[solucio[i]][j]) { 
                        s = s + recursos.getRecurs(j).getNom() + " ";
                        //System.out.print(nom_recurs + " ");
                    }
                }
                s = s + "\n";
                //System.out.println();
            }
        }
        return s;
    }
}