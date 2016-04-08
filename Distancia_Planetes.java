/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vialactea;

/**
 *
 * @author jonatan.lopez.gonzalez
 */

public class Distancia_Planetes {   
    private Posicio[] pos;
    private double[][] matdis;
    public Distancia_Planetes(int nplanetes) {
        matdis = new double[nplanetes][nplanetes];
        pos = new Posicio[nplanetes];
        //dist = 0.00;
    }

    public void leer_Posiciones(Planeta[] planetes) {
        for (int i = 0; i < planetes.length; ++i) {
            pos[i] = planetes[i].getPos();
        }
    }
    public void normalitza_matriu(double max) {
        for (int i = 0; i < matdis.length; ++i) {
            for (int j = 0; j < matdis.length; ++j) {
                matdis[i][j] /= max;
            }
        }
    }
    // La posicion del vector nos dirà el identificador del planeta.
    // La distancia euclidia es la raiz de la suma de la diferencia
    // de cada coordenada al cuadrado. d(P1,P2) -> sqrt((x1-x2)² + (y2-y1)²) 
    public void distancia_Euclidea() {
        double x1, x2, y1 ,y2;
        double d, max;
        max = -1;
        for (int i=0; i<pos.length; ++i) {
            for (int j=0; j < pos.length; ++j) {
                
                x1 = (double) pos[i].getPosx();
                x2 = (double) pos[j].getPosx();
                y1 = (double) pos[i].getPosy();
                y2 = (double) pos[j].getPosy();
                d = Distancia.euclidea(x1, y1, x2, y2);
                if (d > max) max = d;
                matdis[i][j] = d;
            }
        }
        normalitza_matriu(max);
    }
    public void escribir_Matdis() {
        for (int i=0; i<pos.length; ++i) {
            for (int j=0; j < pos.length; ++j) {
                System.out.print(" " + matdis[i][j]);
            }
            System.out.println();
        }
    }
    
    public double consulta_distancia(int idplaneta1, int idplaneta2) {
        double dis = matdis[idplaneta1][idplaneta2];
        return dis;
    }
    
    public double [][] get_distancia_planetes(){
        return matdis;
    }
    /*public void escribir_posiciones() {
        for (int i = 0; i < pos.length; ++i) {
            System.out.println(" " + pos[i].getPosx() + ", " + pos[i].getPosy());
        }
    }*/

    public String LlistarMatriu() {
        //To change body of generated methods, choose Tools | Templates.
        String s = "Matriu distància planetes normalitzada\n";
        for (int i=0; i<pos.length; ++i) {
            for (int j=0; j < pos.length; ++j) {
                s = s + (matdis[i][j] + "      " );
            }
            s=s+"\n";
        }
        return s;
    }
}
