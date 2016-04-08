/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vialactea;

/**
 *
 * @author macia
 */
public class Distancia_Paquets {
    private final double[][] distancia_paquets;
    private int npaquets;
    
    private void matriu_simetrica(double[][] matriu){
        for (int i=0; i<matriu.length; ++i) {
            for (int j=0; j<matriu.length; ++j) {
                matriu[i][j] = (matriu[i][j] + matriu[j][i])/2;
                matriu[j][i] = matriu[i][j];
            }
        }
    }
    
    private void normalitzar_matriu(double[][]matriu, double maxim) {
        for (int i=0; i<matriu.length; ++i) {
            for (int j=0; j<matriu.length; ++j) {
                matriu[i][j] /= maxim; 
            }
        }
    }
    
    public void init(){
        for(int i=0; i<npaquets; ++i){
            for (int j=0; j < npaquets; ++j) {
                distancia_paquets[i][j] = 0;
            }
        }
    }
    
    public Distancia_Paquets(int Npaquets, int nplanetes) {
        distancia_paquets = new double[nplanetes][nplanetes];
        npaquets = Npaquets;
    }
    
    public void distancia_paquets(Paquet paquets) {
        double distancia = 0;
        double dist_max = 0;
        boolean [][] matriu_paquets;
        
        matriu_paquets = paquets.get_matriu();
        
        int Ncol = matriu_paquets[0].length;
        int Nfil = matriu_paquets.length;
        for (int i=0; i<npaquets; ++i) {
            for (int j=0; j<npaquets; ++j) {
                distancia = 0;
                for (int k=0; k<Ncol; ++k) {
                        if (matriu_paquets[i][k] && !(matriu_paquets[j][k])) {
                            distancia++;
                        }
                }
                if (distancia > dist_max) dist_max = distancia;
                distancia_paquets[i][j] = distancia;
            }
        }
        matriu_simetrica(distancia_paquets);
        if (dist_max != 0) normalitzar_matriu(distancia_paquets, dist_max);
            int posicions_a_emplenar = Ncol-(Ncol-npaquets);
            for (int m=posicions_a_emplenar; m<distancia_paquets.length; m++) {
                for (int n=0; n<distancia_paquets.length; n++) {
                //System.out.println("m: " + m + " n: " + n);
                //System.out.println("NCol: " + Ncol);
                distancia_paquets[m][n] = 1;
                distancia_paquets[n][m] = 1;
                if (m == n) distancia_paquets[m][n] = 0;
                }
            }
    }
    
    public void escribir_Matdist() {
        for (int i=0; i<distancia_paquets.length; ++i) {
            for (int j=0; j < distancia_paquets.length; ++j) {
                System.out.print(" " + distancia_paquets[i][j]);
            }
            System.out.println();
        }
    }
    
    public double [][] get_distancia_paquets(){
        return distancia_paquets;
    }

    public String LlistaMat() {
        //To change body of generated methods, choose Tools | Templates.
                String s = "Matriu distància paquets normalitzada\n";
        for (int i=0; i<distancia_paquets.length; ++i) {
            for (int j=0; j < distancia_paquets.length; ++j) {
                s = s + (distancia_paquets[i][j] + "      " );
            }
            s=s+"\n";
        }
        return s;
    }
}

